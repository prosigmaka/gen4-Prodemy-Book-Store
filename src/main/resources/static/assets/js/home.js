var totalQuantity = 0;


$.ajax({
    url: '/api/book',
    method: 'get',
    contentType: 'application/json',
    success: function (res, status, xhr) {
        var i;
        if (xhr.status == 200 || xhr.status == 201) {
            for (i = 0; i < 8; i++) {
                document.getElementById('best-seller').innerHTML += '<div class="col-md-3 pro-1" onclick=bookDescription("' + res[i].id + '");>' +
                    "<div class='col-m'>" +
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
                    "<div class='mid-1'>" +
                    "<div class='women' onclick=bookDescription('" + res[i].id + "');>" +
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
    $('#modal-description-data').text(id);
    console.log(id)
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
                getCart();
                console.log(idChecked);
                console.log('telah dihapus');
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
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < res.length; i++) {
                    totalQuantity += res[i].kuantitasBuku;
                    document.getElementById('cartTable').innerHTML += '<div class="container-fluid">' +
                        '<div class="row">' +
                        '<div class="col-sm-1">' +
                        '<input class="cart-id" type="checkbox" value="' + res[i].id + '">' +
                        '<input class="book-id" name="id" value="'+res[i].idBuku+'" type="hidden"/>' +
                        '</div>' +
                        '<div class="col-sm-2">' +
                        "Gambar" +
                        '</div>' +
                        '<div class="col-sm-3">' +
                        res[i].judulBuku +
                        '</div>' +
                        '<div class="col-sm-1">' +
                        '<input class="book-quantity" type="number" value="'+res[i].kuantitasBuku+'" aria-valuemin=1>' +
                        '</div>' +
                        '<div class="col-sm-1">' +
                        res[i].hargaBuku +
                        '</div>' +

                        '</div>' +
                        '</div>'
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

function saveCart() {
    $.each($('.book-id'), function (i) {
        console.log($(this).val());
    })
    $.each($('.book-quantity'), function (i) {
        console.log($(this).val());
    })
    $.each($('.cart-id'), function (i) {
        console.log($(this).val());
    })

    $('#modal-cart').modal('hide')
}

