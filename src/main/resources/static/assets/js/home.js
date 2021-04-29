$.ajax({
    url: '/api/book',
    method: 'get',
    contentType: 'application/json',
    success: function (res, status, xhr) {
        var i;
        if (xhr.status == 200 || xhr.status == 201) {
            for (i = 0; i < 8; i++) {
                document.getElementById('best-seller').innerHTML += '<div class="col-md-3 pro-1" onclick=bookDescription("'+res[i].id+'");>' +
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
                    '<div class="add add-2"><button type="button" class="btn btn-add-to-cart" id="add-to-cart" onclick=addToCart("'+res[i].id+'");>'+'Add to Cart</button>' +
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
            var bs;
            var i;
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < 4; i++) {
                    document.getElementById('recommendation').innerHTML += '<div class="col-md-3 pro-1">' +
                        "<div class='col-m'>" +
                        "<div class='mid-1'>" +
                        "<div class='women' onclick=bookDescription('"+res[i].id+"');>" +
                        "<h6>" + res[i].judulBuku + "</h6>" +
                        "</div>" +
                        "<div class='mid-2'>" +
                        "<p><em class='item_price'>" + "Rp " + res[i].hargaBuku + "</em></p>" +
                        "<div class='block'>" +
                        "<div class='starbox small ghosting'></div>" +
                        "</div>" +
                        "<div class='clearfix'></div>" +
                        "</div>" +
                        '<div class="add add-2"><button type="button" class="btn btn-add-to-cart" id="add-to-cart" onclick=addToCart("'+res[i].id+'");>'+'Add to Cart</button>' +
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
    }
);

$("#show-cart").click(function () {
    $("#modal-cart").modal('show')
})

function addToCart(id){
    var jsonAddToCart = {"id":id, "idBuku":id, "kuantitasBuku":1};
    $.ajax({
        url: '/api/cart/add-direct',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonAddToCart),
        success: function (res, status, xhr) {
            var i;
            if (xhr.status == 200 || xhr.status == 201) {
                console.log(jsonAddToCart);
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function bookDescription(id){
    console.log(id)
    var jsonData = {"id":null, "idUser":null, "idBuku":id};
    $.ajax({
        url: '/api/rekomendasi',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (res, status, xhr) {
            console.log("step3")
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



