
$(function () {
    // bind listener to edit button
    $(".edit-button").click(function (event) {
        clearModal();
        var form = $("#info-form");
        var entry = $(this).parent().parent();

        // change modal title
        $(".modal-title").text("编辑菜品");

        // add specific food id to a hidden input of form
        $("form .hidden").remove();
        form.append($("<input type='number' name='foodId' class='hidden' value='" + entry.find(".food-id").text() + "'/>"));
        form.append($("<input type='text' name='coverFilename' class='hidden' value='" + entry.find(".cover-filename").text() + "'/>"));
        // change action attribute
        form.attr("action", "/admin/foods/update");

        // fill context of the form
        $("#food-title").val(entry.find(".food-title").text());
        $("#isbn").val(entry.find(".isbn").text());
        $("#author").val(entry.find(".author").text());
        $("#price").val(entry.find(".price").text());
        $("#stock").val(entry.find(".stock").text());
        $("#for-sale").prop("checked", entry.find(".for-sale").text() === "true");
        $("#description").val(entry.find(".description").text());
        $("#category").val(entry.find(".category").text());
    });

    // bind listener to add button
    $(".add-button").click(function (event) {
        clearModal();
        var form = $("#info-form");

        // change modal title
        $(".modal-title").text("Add food");

        // remove hidden food id
        $("form .hidden").remove();
        form.append($("<input type='text' name='coverFilename' class='hidden' value=''/>"));
        // change action attribute
        form.attr("action", "/admin/foods/add");
    });

    // bind listener to remove button
    $(".remove-button").click(function (event) {
        var entry = $(this).parent().parent();
        var id = entry.find("th").text();

        var form = $('<form class="hidden" action="/admin/foods/delete" method="post">' +
            '<input type="number" name="foodId" value="' + id + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    });

    // bind listener to submit button
    $("#modal-submit-button").click(function (event) {
        $("#price").val(Math.round($("#price").val() * 100));
        var form = $("#info-form");
        form.append($(
            '<input type="number" name="shopId" value="' + getQueryVariable("shopId") + '" />' ))
        form.submit();
    })

    $('input[name=fileUpload]').on('change',function(event){
        let data = new FormData();
        data.append('file', event.target.files[0])
        let entry = $(this).parent().parent();
        let id = entry.find("th").text();
        $.ajax({
            url: "/image/avatar/food?foodId="+id,
            type: "POST",
            data: data,
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {
                if (data === true) {
                    window.location.reload()
                }
            }
        });
    })
});

// clear form in modal
function clearModal() {
    $("#for-sale").prop("checked", false);
    $("input").val("");
    $("textarea").val("");
}

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}
