$('#ver_detalle').on('show.bs.modal', function(event) {
    let button = $(event.relatedTarget);

    $("#procesoventa_id").val(button.data('proceso_venta_id'));
    $("#producto_descripcion").val(button.data('producto'));
    $("#producto_kilogramos").val(button.data('kilogramos'));
    $("#fechasolicitud").val(button.data('fechacreacion'));


    $.ajax({
        url : "/transportista/subastas/detalle",
        data : {
            proceso_venta_id : button.data('proceso_venta_id')
        },
        dataType : "json",
        success : function(data) {
            $('#tabla_dinamica_ver_detalle').empty();
            var trHTML = '';
            $.each(data, function(i, item) {
                trHTML += '<tr><td>'
                    + item.productor.razonsocial
                    + '</td><td>'
                    + item.kilogramosocupados
                    + '</td><td>'
                    + item.productor.direccion
                    + '</td><td>'
                    + item.productor.comuna
                    + '</td></tr>';
            });
            $('#tabla_dinamica_ver_detalle').append(trHTML);
        }
    });
});


$('.btn_ofertar').click(function (e) {
    e.preventDefault();
    $('.btn_ofertar').prop('disable', true);

    const valor_ofertado = parseInt($('#precio_a_ofertar').val(), 10);

    if(isNaN(valor_ofertado) || valor_ofertado <= 0){
        MENSAJE_ERROR('','Ingresa un valor de oferta válido para esta subasta.');
        return;
    }

    Swal.fire({
        title: '',
        text: "¿Realmente desea ofertar a la subasta con el precio ofrecido?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: "Ofertar",
        confirmButtonColor: '#28a745',
        cancelButtonText: 'Cerrar',
        cancelButtonColor: '#6c757d'
    }).then((result) => {
        if (result.value) {
            respuestaOferta(valor_ofertado);
        }else{
            $('.btn_ofertar').prop('disable', false);
        }
    })

    const respuestaOferta = function(valor_ofertado){
        $.ajax({
            url : "/transportista/subasta/ofertar",
            data : {
                proceso_venta_id : $('#procesoventa_id').val(),
                valoroferta: valor_ofertado
            },
            dataType : "json",
            success : function(data) {
                if (data === -2) {
                    MENSAJE_ERROR('','Sólo los usuarios transportistas pueden ofertar a una subasta.');
                }
                if (data === -1) {
                    MENSAJE_ERROR('','No se ha podido ofertar a la subasta, favor contacta al administrador.');
                }else if(data === 1){
                    MENSAJE_SUCCESS('',
                        'Se ha ofertado satisfactoriamente a la subasta actual',
                        'Cerrar',
                        'transportista/subastas');
                }else {
                    MENSAJE_ERROR('','Hubo un problema en el sistema, favor contacta con el administrador si ves este mensaje (COD: 10501).');
                }
                $('.btn_ofertar').prop('disable', false);
            }
        });
    }
});