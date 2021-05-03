var totalPrice = 0
var shoppingCI = {
    dashboard: function () {
        $.ajax({
            url: '/api/ci/checkout-item',
            method: 'post',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                var i;
                if (xhr.status == 200 || xhr.status == 201) {
                    for (i = 0; i < res.length; i++) {
                        document.getElementById('co-form').innerHTML +=
                            '<div className="col-md-8 order-md-1 validform2">' +

                                '<h4 className="d-flex justify-content-between align-items-center mb-3">' +
                                    '<span className="text-muted">'+ 'Your cart'+'</span>' +
                                    '<span className="text-right">'+ res.length + '</span>' +
                                '</h4>' +
                                '<ul className="list-group mb-3">' +
                                    '<li className="list-group-item d-flex justify-content-between lh-condensed">' +
                                        '<div>' +
                                            '<h6 className="my-0">' + res[i].judulBuku + '</h6>' +
                                            '<small className="text-muted">'+ res[i].kuantitasBuku +"</small>" +
                                        "</div>" +
                                        '<span className="text-muted">' +'Rp.'+ res[i].subTotalHargaBuku + '</span>' +
                                    "</li>" +
                                "</ul>" +

                                '<hr className="mb-md-2">' +
                                '<ul className="list-group mb-3">' +

                                    '<li className="list-group-item d-flex justify-content-between">' +
                                        "<span>"+"Total (RUPIAH)"+"</span>" +
                                    "</li>" +
                                    '<li className="list-group-item d-flex justify-content-between lh-condensed">' +
                                        "<div>" +
                                            "<strong>"+'Rp.'+ totalPrice += res[i].subTotalHargaBuku +"</strong>"+
                                        "</div>" +
                                    "</li>" +
                                "</ul>" +
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

}

var shoppingCO = {
    dashboard: function () {
        $.ajax({
            url: '/api/ci/checkout-item',
            method: 'post',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                var i;
                if (xhr.status == 200 || xhr.status == 201) {
                    for (i = 0; i < res.length; i++) {
                        document.getElementById('co-form').innerHTML +=
                            '<div className="col-md-8 order-md-1 validform2">' +

                            '<h4 className="d-flex justify-content-between align-items-center mb-3">' +
                            '<span className="text-muted">'+ 'Your cart'+'</span>' +
                            '<span className="text-right">'+ res.length + '</span>' +
                            '</h4>' +
                            '<ul className="list-group mb-3">' +
                            '<li className="list-group-item d-flex justify-content-between lh-condensed">' +
                            '<div>' +
                            '<h6 className="my-0">' + res[i].judulBuku + '</h6>' +
                            '<small className="text-muted">'+ res[i].kuantitasBuku +"</small>" +
                            "</div>" +
                            '<span className="text-muted">' +'Rp.'+ res[i].subTotalHargaBuku + '</span>' +
                            "</li>" +
                            "</ul>" +

                            '<hr className="mb-md-2">' +
                            '<ul className="list-group mb-3">' +

                            '<li className="list-group-item d-flex justify-content-between">' +
                            "<span>"+"Total (RUPIAH)"+"</span>" +
                            "</li>" +
                            '<li className="list-group-item d-flex justify-content-between lh-condensed">' +
                            "<div>" +
                            "<strong>"+'Rp.'+ totalPrice += res[i].subTotalHargaBuku +"</strong>"+
                                "</div>" +
                                "</li>" +
                                "</ul>" +
                                "</div>"
                    }

                } else {
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    },

    insertOrder: function () {
        $.ajax({
            url: '/api/co/insert',
            method: 'post',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                var i;
                if (xhr.status == 200 || xhr.status == 201) {
                    for (i = 0; i < res.length; i++) {
                    }

                } else {
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    }

}
