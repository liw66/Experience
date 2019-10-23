$(function () {
    $('#side-menu').metisMenu();

    $("#collapsemenu").click(function () {
        $("body").hasClass("mini-navbar") ?
            $("body").removeClass("mini-navbar")
            :
            $("body").addClass("mini-navbar")
    })
})