function searchBook() {
    console.log("search work")
    var searchKey = $("#search").val();
    console.log(searchKey);
    $('#best-seller').empty();
    $('.product').empty();
    $('#heading-1').text('Search Result');

    $.ajax({
        url: '/api/book/search/' + searchKey,
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            var i;
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < res.length; i++) {
                    document.getElementById('best-seller').innerHTML += '<div class="col-md-3 pro-1" onclick=bookDescription("' + res[i].id + '");>' +
                        "<div class='col-m'>" +
                        "<img class='img-responsive' src='"+res[i].gambar+"' alt  onclick=bookDescription('" + res[i].id + "');>" +
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
}