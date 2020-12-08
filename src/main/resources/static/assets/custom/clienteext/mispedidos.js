$('#ver_detalle').on('show.bs.modal', function(event) {
    let button = $(event.relatedTarget);

    $("#procesoventa_id").val(button.data('proceso_venta_id'));
    $("#producto_descripcion").val(button.data('producto'));
    $("#producto_kilogramos").val(button.data('kilogramos'));
    $("#fechasolicitud").val(button.data('fechacreacion'));


    $.ajax({
        url : "/clienteext/mis-pedidos/detalle",
        data : {
            proceso_venta_id : button.data('proceso_venta_id')
        },
        dataType : "json",
        success : function(data) {
            $('#tabla_dinamica_ver_detalle').empty();
            $('.total_precio_total').empty();
            var total_precio_total = 0;
            var trHTML = '';
            $.each(data, function(i, item) {
                total_precio_total = total_precio_total + (item.kilogramosocupados * item.ingreso.preciokgventaunitario);
                trHTML += '<tr><td>'
                    + item.productor.razonsocial
                    + '</td><td>'
                    + item.kilogramosocupados
                    + '</td><td>'
                    + '$ ' + item.ingreso.preciokgventaunitario
                    + '</td><td>'
                    + '$ ' + (item.kilogramosocupados * item.ingreso.preciokgventaunitario)
                    + '</td></tr>';
            });
            $('#tabla_dinamica_ver_detalle').append(trHTML);
            $('.total_precio_total').append('$ ' + total_precio_total);
        }
    });
});


$('.btn_acepta_propuesta').click(function (e) {
    e.preventDefault();
    $('.btn_acepta_propuesta').prop('disabled', true);
    $('.btn_rechaza_propuesta').prop('disabled', true);
    generarRespuestaAcuerdo(true);
});

$('.btn_rechaza_propuesta').click(function (e) {
    e.preventDefault();
    $('.btn_acepta_propuesta').prop('disabled', true);
    $('.btn_rechaza_propuesta').prop('disabled', true);
    generarRespuestaAcuerdo(false);
});

const generarRespuestaAcuerdo = function (tipoRespuesta) {

    let mensaje = tipoRespuesta ? "aceptar" : "rechazar";
    let color = tipoRespuesta ? '#28a745' : '#dc3545';

    Swal.fire({
        title: '',
        text: "¿Realmente desea " + mensaje +" la propuesta de venta?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: mensaje.charAt(0).toUpperCase() + mensaje.slice(1),
        confirmButtonColor: color,
        cancelButtonText: 'Cerrar',
        cancelButtonColor: '#6c757d'
    }).then((result) => {
        if (result.value) {
            respuestaAcuerdo(tipoRespuesta);
        }else{
            $('.btn_acepta_propuesta').prop('disabled', false);
            $('.btn_rechaza_propuesta').prop('disabled', false);
        }
    })

    const respuestaAcuerdo = function(tipoRespuesta){
        $.ajax({
            url : "/clienteext/mis-pedidos/respuestaAcuerdo",
            data : {
                proceso_venta_id : $('#procesoventa_id').val(),
                tipo_respuesta: tipoRespuesta
            },
            dataType : "json",
            success : function(data) {
                if (data === -1) {
                    MENSAJE_ERROR('','No se ha podido generar la respuesta del acuerdo, favor contacta al administrador.');
                }else if(data === 1){
                    MENSAJE_SUCCESS('',
                        'Se ha generado satisfactoriamente su respuesta al acuerdo',
                        'Cerrar',
                        'clienteext/mis-pedidos');
                }else {
                    MENSAJE_ERROR('','Hubo un problema en el sistema, favor contacta con el administrador si ves este mensaje (COD: 10500).');
                }
                $('.btn_acepta_propuesta').prop('disabled', false);
                $('.btn_rechaza_propuesta').prop('disabled', false);
            }
        });
    }
};
