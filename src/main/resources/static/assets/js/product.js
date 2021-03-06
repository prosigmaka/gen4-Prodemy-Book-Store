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
                    "<td>" + "<img class='img-responsive' src='"+res[i].gambar+"' style='width: 80px; height: 80px' alt>" + "</td>" +
                    "<td>" + res[i].namaPengarang + "</td>" +
                    "<td>" + res[i].namaPenerbit + "</td>" +
                    "<td>" + res[i].stokBuku + "</td>" +
                    "<td>" + res[i].hargaBuku + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-brown' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                    "</svg>" +
                    "</button>" +
                    "<button type='button' class='btn btn-white-brown' onclick=bookCRUD.deleteBook('" + res[i].id + "')>" +
                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash-fill' viewBox='0 0 16 16'><path d='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z'/>" +
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


var bookCRUD = {
    editBook: function (id) {
        $.ajax({
            url: '/api/book/' + id,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    document.getElementById('id').value = id;
                    document.getElementById('judulBuku').value = res.judulBuku;
                    document.getElementById('namaPengarang').value = res.author.namaPengarang;
                    document.getElementById('namaPenerbit').value = res.publisher.namaPenerbit;
                    document.getElementById('namaKategori').value = res.category.namaKategori;
                    document.getElementById('tahunTerbit').value = res.tahunTerbit;
                    document.getElementById('stokBuku').value = res.stokBuku;
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

    saveBook: function () {
        console.log("step1")
        var dataResult = getJsonForm($("#form-book").serializeArray(), true);
        console.log(dataResult)
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
                    // var table = document.getElementById('authorTable').getElementsByTagName('tbody')[0];
                    // table.innerHTML = "";
                    $('#bookTable').empty();
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
                                        "<td>" + "<img class='img-responsive' src='"+res[i].gambar+"' style='width: 80px; height: 80px' alt>" + "</td>" +
                                        "<td>" + res[i].namaPengarang + "</td>" +
                                        "<td>" + res[i].namaPenerbit + "</td>" +
                                        "<td>" + res[i].stokBuku + "</td>" +
                                        "<td>" + res[i].hargaBuku + "</td>" +
                                        "<td>" +
                                        "<button type='button' class='btn btn-brown' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
                                        "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                                        "</svg>" +
                                        "</button>" +
                                        "<button type='button' class='btn btn-white-brown' onclick=bookCRUD.deleteBook('" + res[i].id + "')>" +
                                        "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='#66512c' class='bi bi-trash-fill' viewBox='0 0 16 16'><path d='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z'/>" +
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
    },

    deleteBook: function (id) {
        $.ajax({
            url: '/api/book/' + id,
            method: 'delete',
            success: function () {
                var table = document.getElementById('authorTable').getElementsByTagName('tbody')[0];
                table.innerHTML = "";
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
                                    "<td>" + "<img class='img-responsive' src='"+res[i].gambar+"' style='width: 80px; height: 80px' alt>" + "</td>" +
                                    "<td>" + res[i].namaPengarang + "</td>" +
                                    "<td>" + res[i].namaPenerbit + "</td>" +
                                    "<td>" + res[i].stokBuku + "</td>" +
                                    "<td>" + res[i].hargaBuku + "</td>" +
                                    "<td>" +
                                    "<button type='button' class='btn btn-brown' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
                                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                                    "</svg>" +
                                    "</button>" +
                                    "<button type='button' class='btn btn-white-brown' onclick=bookCRUD.deleteBook('" + res[i].id + "')>" +
                                    "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash-fill' viewBox='0 0 16 16'><path d='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z'/>" +
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
                console.log("delete berhasil")
            },
            erorrr: function (err) {
                console.log(err);
            }
        });

    },

    addBook: function () {
        $('#form-book')[0].reset();
        $('#id').val = null;
        $('#modal-book').modal('show');
    }
}

function searchInTable() {
    $('#bookTable').empty()
    var text = $('#search-input').val();

    $.ajax({
        url: '/api/book',
        method: 'get',
        contentType: 'application/json',
        success: function (res, status, xhr) {
            var bs;
            var i;
            if (xhr.status == 200 || xhr.status == 201) {
                for (i = 0; i < res.length; i++) {
                    if (res[i].judulBuku.indexOf(text) !== -1 || res[i].namaPengarang.indexOf(text) !== -1 || res[i].namaPenerbit.indexOf(text) !== -1) {
                        document.getElementById('bookTable').innerHTML += "<tr>" +
                            // "<th scope='row'>" + res[i].id + "</th>" +
                            "<td>" + res[i].judulBuku + "</td>" +
                            "<td>" + "<img class='img-responsive' src='"+res[i].gambar+"' style='width: 80px; height: 80px' alt>" + "</td>" +
                            "<td>" + res[i].namaPengarang + "</td>" +
                            "<td>" + res[i].namaPenerbit + "</td>" +
                            "<td>" + res[i].stokBuku + "</td>" +
                            "<td>" + res[i].hargaBuku + "</td>" +
                            "<td>" +
                            "<button type='button' class='btn btn-brown' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
                            "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-pencil' viewBox='0 0 16 16'><path d='M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z'/>" +
                            "</svg>" +
                            "</button>" +
                            "<button type='button' class='btn btn-white-brown' onclick=bookCRUD.deleteBook('" + res[i].id + "')>" +
                            "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-trash-fill' viewBox='0 0 16 16'><path d='M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z'/>" +
                            "</svg>" +
                            "</button>" +
                            "</td>" +
                            "</tr>"
                    }
                }
            } else {
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}



