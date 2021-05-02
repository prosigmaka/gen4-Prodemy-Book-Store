function pageCategories(categoryName) {
    $.ajax({
        url: '/api/book/page/' + categoryName,
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            var i;
            var category = categoryName.toLowerCase() + '-category';
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < 8; i++) {
                    document.getElementById(category).innerHTML += '<div class="col-md-3 pro-1" onclick=bookDescription("' + res[i].id + '");>' +
                        "<div class='col-m'>" +
                        "<img class='img-responsive' src='"+res[i].gambar+"' alt style='height: 200px'>" +
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

pageCategories('Academic')
pageCategories('Comic')
pageCategories('Family')
pageCategories('Novel')



