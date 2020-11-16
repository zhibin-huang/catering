
$(function () {
    // bind listener to edit button
    $(".edit-button").click(function (event) {
        clearModal();
        var form = $("#info-form");
        var entry = $(this).parent().parent();

        // change modal title
        $(".modal-title").text("编辑店铺");

        // add specific shop id to a hidden input of form
        $("form .hidden").remove();
        form.append($("<input type='number' name='shopID' class='hidden' value='" + entry.find(".shop-id").text() + "'/>"));
        form.append($("<input type='text' name='coverFilename' class='hidden' value='" + entry.find(".cover-filename").text() + "'/>"));
        // change action attribute
        form.attr("action", "/admin/shops/update");

        // fill context of the form
        $("#shop-title").val(entry.find(".shop-title").text());
        $("#city").val(entry.find(".city").text());
        $("#district").val(entry.find(".district").text());
        $("#landmark").val(entry.find(".landmark").text());
        $("#address").val(entry.find(".address").text());
        $("#star-rate").val(entry.find(".star-rate").text());
        $("#description").val(entry.find(".description").text());
        $("#food-tag").val(entry.find(".food-tag").text());
    });

    // bind listener to add button
    $(".add-button").click(function (event) {
        clearModal();
        var form = $("#info-form");

        // change modal title
        $(".modal-title").text("Add shop");

        // remove hidden shop id
        $("form .hidden").remove();
        form.append($("<input type='number' name='coverFilename' class='hidden' value=''/>"));
        // change action attribute
        form.attr("action", "/admin/shops/add");
    });

    // bind listener to remove button
    $(".remove-button").click(function (event) {
        var entry = $(this).parent().parent();
        var id = entry.find("th").text();
        alert(id);
        var form = $('<form class="hidden" action="/admin/shops/delete" method="post">' +
            '<input type="number" name="shopId" value="' + id + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    });

    // bind listener to submit button
    $("#modal-submit-button").click(function (event) {
        $("#info-form").submit();
    })

    $('input[name=fileUpload]').on('change',function(e){
            let data = new FormData();
            data.append('file', e.target.files[0])
            let entry = $(this).parent().parent();
            let id = entry.find("th").text()
            $.ajax({
                url: "/image/avatar/shop?shopId="+id,
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
    $("input").val("");
    $("textarea").val("");
}
