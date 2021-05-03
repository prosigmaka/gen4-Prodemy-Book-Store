var totalQuantity = 0;
var delQuantity = 0;


$.ajax({
    url: '/api/book',
    method: 'get',
    contentType: 'application/json',
    success: function (res, status, xhr) {
        var i;
        if (xhr.status == 200 || xhr.status == 201) {
            for (i = 0; i < 8; i++) {
                document.getElementById('best-seller').innerHTML += '<div class="col-md-3 pro-1" style="border-color: saddlebrown; border-width: 4px">' +
                    "<div class='col-m'>" +
                    "<img class='img-responsive' src='" + res[i].gambar + "' alt style='height: 200px'  onclick=bookDescription(" + res[i].id + ");>" +
                    "<div class='mid-1'>" +
                    "<div class='women'>" +
                    "<h6>" + res[i].judulBuku + "</h6>" +
                    "</div>" +
                    "<div class='mid-2'>" +
                    "<p><em class='item_price'>" + "Rp " + res[i].hargaBuku + "</em></p>" +
                    "<div class='block'>" +
                    "<div class='starbox small ghosting'></div>" +
                    "</div>" +
                    "<div class='clearfix'></div>" +
                    "</div>" +
                    '<div class="add add-2"><button type="button" class="btn btn-add-to-cart" id="add-to-cart" onclick=addToCart("' + res[i].id + '");>' + 'Add to Cart</button>' +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>"
            }

        } else {
        }
    },
    error: function (err) {
        console.log(err);
    }
});

$.ajax({
    url: '/api/rekomendasi',
    method: 'get',
    contentType: 'application/json',
    success: function (res, status, xhr) {
        var i;
        if (xhr.status == 200 || xhr.status == 201) {
            for (i = 0; i < 4; i++) {
                document.getElementById('recommendation').innerHTML += '<div class="col-md-3 pro-1">' +
                    "<div class='col-m'>" +
                    "<img class='img-responsive' src='" + res[i].gambar + "' alt style='height: 200px' onclick=bookDescription('" + res[i].id + "');>" +
                    "<div class='mid-1'>" +
                    "<div class='women'>" +
                    "<h6>" + res[i].judulBuku + "</h6>" +
                    "</div>" +
                    "<div class='mid-2'>" +
                    "<p><em class='item_price'>" + "Rp " + res[i].hargaBuku + "</em></p>" +
                    "<div class='block'>" +
                    "<div class='starbox small ghosting'></div>" +
                    "</div>" +
                    "<div class='clearfix'></div>" +
                    "</div>" +
                    '<div class="add add-2"><button type="button" class="btn btn-add-to-car" id="add-to-cart" onclick=addToCart("' + res[i].id + '");>' + 'Add to Cart</button>' +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>"
            }

        } else {
        }
    },
    error: function (err) {
        console.log(err);
    }
});

function getCart() {
    $.ajax({
        url: '/api/cart',
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            if (xhr.status == 200 || xhr.status == 201) {
                for (var i = 0; i < res.length; i++) {
                    totalQuantity += res[i].kuantitasBuku;
                }
                $('#total-quantity-badge').text(totalQuantity);
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }

    })
}

getCart();

$("#show-cart").click(function () {
    $("#modal-cart").modal('show')
    showTableCart();
})

function addToCart(id) {
    var jsonAddToCart = {"addToCart": [{"id": id, "idBuku": id, "kuantitasBuku": 1}]};
    $.ajax({
        url: '/api/cart',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonAddToCart),
        success: function (res, status, xhr) {
            var i;
            if (xhr.status == 200 || xhr.status == 201) {
                totalQuantity += 1;
                $('#total-quantity-badge').text(totalQuantity);
                console.log(jsonAddToCart);
                $('#modal-book-description').modal('hide');
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function bookDescription(id) {
    $('#modal-book-description').modal('show');
    $.ajax({
        url: '/api/book/' + id,
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            if (xhr.status == 200 || xhr.status == 201) {
                $('#modal-description-image').html("<img class='img-responsive' src='" + res.gambar + "' alt style='height: 100px; width: 90px'>");
                $("#modal-book-title").text(res.judulBuku);
                $("#modal-book-author").text(res.author.namaPengarang);
                $("#modal-book-publisher").text(res.publisher.namaPenerbit);
                $("#modal-book-price").text("Rp " + res.hargaBuku);
                console.log(res.publisher.namaPenerbit, res.author.namaPengarang, res.category.namaKategori)
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
    var jsonData = {"id": null, "idUser": null, "idBuku": id};
    $.ajax({
        url: '/api/rekomendasi',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (res, status, xhr) {
            if (xhr.status == 200 || xhr.status == 201) {
                console.log("success to send id!");

            } else {

            }
        },
        erorrr: function (err) {
            console.log(err);
        }
    });
}

function deleteCart() {
    $(':checkbox:checked').each(function (id) {
        var idChecked = $(this).val();
        $.ajax({
            url: '/api/cart/' + idChecked,
            method: 'delete',
            success: function (data, status, xhr) {
                getCart()
                $('#modal-cart').modal('hide');
            },
        })
    })
}

function showTableCart() {
    $('#cartTable').empty();
    $.ajax({
        url: '/api/cart',
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            var i;
            var totalQuantity = 0;
            var totalPrice = 0;
            var priceArray = [];
            var subTotalPriceAarray = [];
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < res.length; i++) {
                    totalQuantity += res[i].kuantitasBuku;
                    var idSubPrice = "price-" + i.toString();
                    priceArray[i] = res[i].hargaBuku;
                    document.getElementById('cartTable').innerHTML += '<div class="container-fluid" style="border: 2px solid #BD9354; border-radius: 10px">' +
                        '<div class="row">' +
                        '<div class="col-sm-1" style="padding: 20px 10px">' +
                        '<input class="cart-id" type="checkbox" value="' + res[i].id + '">' +
                        '<input class="book-id" name="id" value="' + res[i].idBuku + '" type="hidden"/>' +
                        '</div>' +
                        "<div class='col-sm-2' style='padding: 10px 0px; align-content: center'>" +
                        "<img src='" + res[i].gambar + "' alt style='height: 40px; width: 50px'>" +
                        "</div>" +
                        '<div class="col-sm-3" style="padding: 20px 0px; text-align: center ">' +
                        res[i].judulBuku +
                        '</div>' +
                        '<div class="col-sm-3" style="padding: 20px 0px">' +
                        '<input class="book-quantity" type="number" min="1" step="any" id="' + i + '" style="width: 50px; border-radius: 10px" value="' + res[i].kuantitasBuku + '" aria-valuemin=1>' +
                        '</div>' +
                        '<div class="col-sm-1" style="padding: 20px 0px">' +
                        '<p id="' + idSubPrice + '">' + res[i].hargaBuku +
                        '</p>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '<hr>'

                }
                var values = 0;
                var idQ = "";
                $('#cartTable > input').on('mouseup',function () {
                    idQ = $(this).attr('id');
                    var idP = "#price-" + idQ;
                    values = priceArray[parseInt(idQ)] * $(this).val();
                    $(idP).text(values);
                });

                $('#total-quantity-badge').text(totalQuantity);
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }

    })
}



function saveCart() {
    var jsonCart = {};
    var arrayList = [];
    var idBuku = [];
    var kuantitasBuku = [];
    var idKeranjang = [];
    $.each($('.book-id'), function (i) {
        idBuku[i] = $(this).val()
    })
    $.each($('.book-quantity'), function (i) {
        kuantitasBuku[i] = $(this).val()
    })
    $.each($('.cart-id'), function (i) {
        idKeranjang[i] = null

    })
    for (var i = 0; i < idKeranjang.length; i++) {
        var fillArray = {};
        fillArray.id = idKeranjang[i]
        fillArray.idBuku = idBuku[i]
        fillArray.kuantitasBuku = kuantitasBuku[i]

        console.log(fillArray)
        arrayList.push(fillArray)
    }
    jsonCart.addToCart = arrayList
    $('#modal-cart').modal('hide')
    $.ajax({
        url: '/api/cart',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonCart),
        success: function (data, status, xhr) {
            var quantity = 0;
            if (xhr.status == 200 || xhr.status == 201) {
                for (var i = 0; i < data.length; i++) {
                    quantity += data[i].kuantitasBuku;
                }
                $('#total-quantity-badge').text(quantity);
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}
