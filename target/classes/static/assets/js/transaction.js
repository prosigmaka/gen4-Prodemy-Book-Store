$('#btn-tambah-mahasiswa').click(function () {
    var form = this;
    var rowsel =tableKeranjang.column(0).checkboxes.selected();
    $.each(rowsel, function (index, rowId) {
        $(form).append(
            $('<input>').attr('type','hidden').attr('name','id[]').val(rowId)
        )
        
    })
});
var tableKeranjang = {
    create: function () {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#tableKeranjang')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableKeranjang').DataTable().clear();
            $('#tableKeranjang').DataTable().destroy();
            // var kodeMahasiswa = $('#id').val().toString();
        }

        $.ajax({
            url: '/api/co',
            method: 'post',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#tableMahasiswa').DataTable({
                        data: res,
                        columns: [
                            {
                                targets:0,
                                checkboxes: {
                                    selectRow: true,
                                    data: "id"
                                },
                            },
                            {
                                title: "Kode Mahasiswa",
                                data: "id"
                            },
                            {
                                title: "Nama Mahasiswa",
                                data: "nama"
                            },
                            {
                                title: "Alamat",
                                data: "alamat"
                            },
                            {
                                title: "Agama",
                                data: "namaAgama"
                            },
                            {
                                title: "Jurusan",
                                data: "namaJurusan"
                            },

                            // {
                            //     title: "Nama",
                            //     data: "nama"
                            // },
                            // {
                            //     title: "Alamat",
                            //     data: "alamat"
                            // },
                            // {
                            //     title: "Golongan Darah",
                            //     data: "golonganDarah"
                            // },
                            // {
                            //     title: "Tempat Lahir",
                            //     data: "tempatLahir"
                            // },
                            {
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='btn-primary' onclick=formMahasiswa.setEditData('" + data.id + "')>Edit</button>" +
                                        "<button class='btn-danger' onclick=deleteMahasiswa.delConfirmation('" + data.id + "')>Delete</button>"+
                                        "<checkbox th: field class='btn-danger' onclick=deleteMahasiswa.delConfirmation('" + data.id + "')>Delete</checkbox>"
                                }
                            }

                        ],
                        select:{
                            style: 'multi'
                        },
                        order: [[1,'asc']]
                    });

                } else {

                }
            },
            error: function (err) {
                console.log(err);
            }
        });

    }
};


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
                        "<td>" + res[i].judulBuku + "</td>" +
                        "<td>" + res[i].namaPengarang + "</td>" +
                        "<td>" +
                        "<button type='button' class='btn btn-success' onclick=bookCRUD.editBook('" + res[i].id + "')>" +
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
                    $('#modal-book').modal('hide')
                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });
    }
}



