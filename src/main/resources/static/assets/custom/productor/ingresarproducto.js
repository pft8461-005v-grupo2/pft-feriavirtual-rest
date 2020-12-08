// Iniciar variables
$("#producto_id").val(-1);

$("#nombrefruta").autocomplete({
    hint: true,
    highlight: true,
    autoFocus : true,
    minLength : 1,
    source : function(request, response) {
        $.ajax({
            url : "/productor/buscar_fruta_existente",
            async: true,
            type : "GET",
            data : {
                fruta : request.term
            },

            dataType : "json",

            success : function(data) {
                response($.map(data, function(item) {
                    return {
                        id: item.id,
                        label: item.descripcion,
                        value : item.descripcion
                    };
                }));
            }
        });
    },
    change: function (event, ui) {
        frutaSeleccionada(event, ui);
    },
    select: function (event, ui) {
        frutaSeleccionada(event, ui);
    }
});

const frutaSeleccionada = function(event, ui) {
    if(ui.item != null) {
        $("#producto_id").val(ui.item.id);
    }else {
        $("#producto_id").val(-1);
    }
};