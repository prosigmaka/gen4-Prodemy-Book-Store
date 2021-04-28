$.ajax({
    url: '/api/book',
    method: 'get',
    contentType: 'application/json',
    success: function (res, status, xhr) {
        var i;
        if (xhr.status == 200 || xhr.status == 201) {
            for (i = 0; i < res.length; i++) {
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
                        '<div class="add add-2"><button type="button" class="btn btn-success" id="add-to-cart" onclick=addToCart("'+res[i].id+'");>'+'Add to Cart</button>' +
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
    $('#modal-cart').modal('show')
}

function bookDescription(id){
    $.ajax({
        url: '/api/book',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
            console.log("step3")
            if (xhr.status == 200 || xhr.status == 201) {
                console.log("berhasil!");
                var table = document.getElementById('authorTable').getElementsByTagName('tbody')[0];
                table.innerHTML = "";
                // $.ajax({
                //     url: '/api/book',
                //     method: 'get',
                //     contentType: 'application/json',
                //     success: function (res, status, xhr) {
                //         var bs;
                //         var i;
                //         if (xhr.status == 200 || xhr.status == 201) {
                //             for (i = 0; i < res.length; i++) {
                //                 document.getElementById('bookTable').innerHTML += "<tr>" +
                //                     "<th scope='row'>" + res[i].id + "</th>" +
                //                     "<td>" + res[i].judulBuku + "</td>" +
                //                     "<td>" + res[i].namaPengarang + "</td>" +
                //                     "<td>" +
                //                     "<button type='button' class='btn btn-success' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
                //                     "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                //                     "</svg>" +
                //                     "</button>" +
                //                     "<button type='button' class='btn btn-danger' onclick=bookCRUD.deleteBook('" + res[i].id + "')>" +
                //                     "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                //                     "</svg>" +
                //                     "</button>" +
                //                     "</td>" +
                //                     "</tr>"
                //             }
                //
                //         } else {
                //         }
                //     },
                //     error: function (err) {
                //         console.log(err);
                //     }
                // });
                $.ajax({
                    url: '/api/book',
                    method: 'get',
                    contentType: 'application/json',
                    success: function (res, status, xhr) {
                        var bs;
                        var i;
                        if (xhr.status == 200 || xhr.status == 201) {
                            for (i = 0; i < res.length; i++) {
                                document.getElementById('bookTable').innerHTML += "<tr>" +
                                    // "<th scope='row'>" + res[i].id + "</th>" +
                                    "<td>" + res[i].judulBuku + "</td>" +
                                    "<td>" + res[i].namaPengarang + "</td>" +
                                    "<td>" + res[i].namaPenerbit + "</td>" +
                                    "<td>" + res[i].stokBuku + "</td>" +
                                    "<td>" + res[i].hargaBuku + "</td>" +
                                    "<td>" +
                                    "<button type='button' class='btn btn-success' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
                                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                                    "</svg>" +
                                    "</button>" +
                                    "<button type='button' class='btn btn-danger' onclick=bookCRUD.deleteBook('" + res[i].id + "')>" +
                                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                                    "</svg>" +
                                    "</button>" +
                                    "</td>" +
                                    "</tr>"
                            }
                        } else {
                        }
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });
                $('#modal-book').modal('hide')
            } else {

            }
        },
        erorrr: function (err) {
            console.log(err);
        }
    });


}
