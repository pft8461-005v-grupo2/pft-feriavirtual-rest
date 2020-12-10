$('#detalle_general').on('show.bs.modal', function(event) {
    let button = $(event.relatedTarget);

    $.ajax({
        url : "/detalle-general",
        data : {
            proceso_venta_id : button.data('proceso_venta_id')
        },
        dataType : "json",
        success : function(res) {
            $('#etapa_dinamica').empty();

            let etapa = res.procesoVenta.etapa;
            let src;

            switch (etapa){
                case 0:
                    src = 'http://localhost:8080/assets/images/etapa_0.png';
                    break;
                case 1:
                case 2:
                    src = 'http://localhost:8080/assets/images/etapa_1.png';
                    break;
                case 3:
                    src = 'http://localhost:8080/assets/images/etapa_2.png';
                    break;
                case 4:
                case 5:
                case 6:
                    src = 'http://localhost:8080/assets/images/etapa_3.png';
                    break;
                case 7:
                    src = 'http://localhost:8080/assets/images/etapa_4.png';
                    break;
                case 8:
                    src = 'http://localhost:8080/assets/images/etapa_5.png';
                    break;
                default:
                    src = 'http://localhost:8080/assets/images/etapa_0.png';
                    break;
            }

            var image = new Image();
            image.src = src;
            image.width = 750;
            $('#etapa_dinamica').append(image);

        }
    });




});