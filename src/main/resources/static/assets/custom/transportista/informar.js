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


$(".btn_informar").click(function (e){
    e.preventDefault();
    $('.btn_informar').prop('disable', true);

    Swal.fire({
        title: '',
        text: "¿Desea informar que el pedido a sido entregado en bodega central?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: "Confirmar entrega",
        confirmButtonColor: '#28a745',
        cancelButtonText: 'Cerrar',
        cancelButtonColor: '#6c757d'
    }).then((result) => {
        if (result.value) {
            respuestaInforme();
        }else{
            $('.btn_ofertar').prop('disable', false);
        }
    })

    const respuestaInforme = function(){
        $.ajax({
            url : "/transportista/subasta/informar-entrega",
            data : {
                proceso_venta_id : $('#procesoventa_id').val()
            },
            dataType : "json",
            success : function(data) {
                if (data === -1) {
                    MENSAJE_ERROR('','No se ha podido informar la entrega, favor contacta al administrador.');
                }else if(data === 1){
                    MENSAJE_SUCCESS('',
                        'Se ha informado al administrador la entrega de los productos satisfactoriamente.',
                        'Cerrar',
                        'transportista/informar');
                }else {
                    MENSAJE_ERROR('','Hubo un problema en el sistema, favor contacta con el administrador si ves este mensaje (COD: 10501).');
                }
                $('.btn_informar').prop('disable', false);
            }
        });
    }
});