$.ajax({
    url: '/api/book',
    method: 'get',
    contentType: 'application/json',
    success: function (res, status, xhr) {
        var bs;
        var i;
        if (xhr.status == 200 || xhr.status == 201) {
            for (i = 0; i < res.length; i++) {
                document.getElementById('best-seller').innerHTML += '<div class="col-md-3 pro-1">' +
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
                    '<div class="add add-2"><button class="btn btn-danger my-cart-btn my-cart-b" data-id="1" data-name="product 1" data-summary="summary 1" data-price="6.00" data-quantity="1" data-image="images/of16.png">Add to Cart</button>' +
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
        url: '/api/book',
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            var bs;
            var i;
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < 4; i++) {
                    document.getElementById('new-arrival').innerHTML += '<div class="col-md-3 pro-1">' +
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
                        '<div class="add add-2"><button type="button" class="btn btn-success" id="add-to-cart" onclick= "addToCart();">Add to Cart</button>' +
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

function addToCart(){
    $('#modal-cart').modal('show')
}
