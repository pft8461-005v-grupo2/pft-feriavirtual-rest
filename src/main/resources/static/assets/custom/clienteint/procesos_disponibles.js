$('#ver_detalle').on('show.bs.modal', function(event) {
    let button = $(event.relatedTarget);

    $("#procesoventa_id").val(button.data('proceso_venta_id'));
    $("#productor_nombre").val(button.data('productor_nombre'));
    $("#productor_direccion").val(button.data('productor_direccion'));
    $("#productor_comuna").val(button.data('productor_comuna'));
    $("#producto_descripcion").val(button.data('producto_descripcion'));
    $("#producto_kilogramos").val(button.data('producto_kilogramos'));
    $("#producto_fechacreacion").val(button.data('producto_fechacreacion'));
    $("#producto_preciounitario").val(button.data('producto_preciounitario'));
    $("#producto_preciototal").val(button.data('producto_preciototal'));


});


$(".btn_solicitar_oc").click(function (e) {
    e.preventDefault();
    $(".btn_solicitar_oc").prop('disable', true);

    Swal.fire({
        title: '',
        text: "¿Realmente desea solicitar una orden de compra de los productos seleccionados?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: "Solicitar",
        confirmButtonColor: '#28a745',
        cancelButtonText: 'Cerrar',
        cancelButtonColor: '#6c757d'
    }).then((result) => {
        if (result.value) {
            respuestaSolicitud();
        }else{
            $('.btn_solicitar_oc').prop('disable', false);
        }
    })

    const respuestaSolicitud = function(){
        $.ajax({
            url : "/clienteint/solicitar-oc",
            data : {
                proceso_venta_id : $('#procesoventa_id').val()
            },
            dataType : "json",
            success : function(data) {
                if (data === -2) {
                    MENSAJE_ERROR('','Proceso de venta no disponible, favor contacta al administrador.');
                }
                if (data === -1) {
                    MENSAJE_ERROR('','No se ha podido solicitar orden de compra, favor contacta al administrador.');
                }else if(data === 1){
                    MENSAJE_SUCCESS('',
                        'Se ha solicitado la orden de compra satisfactoriamente.',
                        'Cerrar',
                        'clienteint/procesos-disponibles');
                }else {
                    MENSAJE_ERROR('','Hubo un problema en el sistema, favor contacta con el administrador si ves este mensaje (COD: 10501).');
                }
                $('.btn_solicitar_oc').prop('disable', false);
            }
        });
    }
})