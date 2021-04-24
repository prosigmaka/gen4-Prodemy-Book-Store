
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
                            "<th scope='row'>" + res[i].id + "</th>" +
                            "<td>" + res[i].judulBuku + "</td>"+
                            "<td>" + res[i].author.namaPengarang + "</td>"+
                            "<td>"+
                                "<button type='button' class='btn btn-success' onclick=bookCRUD.editBook('"+res[i].id+"')>" +
                                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>"+
                                    "</svg>"+
                                "</button>"+
                            "</td>"+
                        "</tr>"
                    }

                } else {
                }
            },
            error: function (err) {
                console.log(err);
            }
        }
    );

var bookCRUD = {
    editBook: function (id) {
        $.ajax({
            url: '/api/book/' + id,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    document.getElementById('judulBuku').value = res.judulBuku;
                    document.getElementById('namaPengarang').value = res.author.namaPengarang;
                    document.getElementById('hargaBuku').value = res.hargaBuku;
                    $('#modal-book').modal('show')
                    console.log('Success!')
                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });
    },

    saveBook: function (id){

    }
}



