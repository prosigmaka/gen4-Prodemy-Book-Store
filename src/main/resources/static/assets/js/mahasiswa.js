var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $.ajax({
            url: '/api/mahasiswa',
            method: 'get',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#tableBiodata').DataTable({
                        data: res,
                        columns: [
                            {
                                title: "Nama",
                                data: "nama"
                            },
                            {
                                title: "Alamat",
                                data: "alamat"
                            },

                            {
                                title: "Agama",
                                data: "agama"
                            },
                            {
                                title: "Jurusan",
                                data : "jurusan"
                            },

                            {
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='btn-primary btn-dark' onclick=formBiodata.setEditData('" + data.id + "')>Edit</button>" +
                                      "<button class='btn-primary btn-danger' onclick=deleteData.deleteModalConfirm('" + data.id + "')>Delete</button>"
                                }
                            }
                        ]
                    });

                } else {

                }
            },
            error: function (err) {
                console.log(err);
            }
        });


    },

};

var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
        $("#id").val("");
        $("#idDetail").val("");
    },
    saveForm: function () {
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
            console.log(dataResult);
            $.ajax({
                url: '/api/mahasiswa',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    if (xhr.status == 200 || xhr.status == 201) {
                        tableBiodata.create();
                        $('#modal-mahasiswa').modal('hide')
                    } else {

                    }
                },
                erorrr: function (err) {
                    console.log(err);
                }
            });
        }
    },
  setEditData: function (id) {
        formBiodata.resetForm();

        $.ajax({
            url: '/api/mahasiswa/' + id,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-mahasiswa').modal('show')

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });


    }

};
var deleteData = {
  deleteModalConfirm: function (id) {
    $.ajax({
      url: '/api/mahasiswa/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-biodata').fromJSON(JSON.stringify(res));
          $('#delete-mahasiswa').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });

  },
  deleteDataRow: function () {
    if ($('#form-biodata').parsley().validate()) {
      var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);
      console.log(dataResult);
      $.ajax({
        url: '/api/mahasiswa/' + dataResult.id,
        method: 'delete',
        success: function () {
            tableBiodata.create();
            $('#delete-mahasiswa').modal('hide')

          },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }

};
