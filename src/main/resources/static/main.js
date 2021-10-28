$('document').ready(function(){
    console.log("The page loaded with the script");
    $('.table #orderButton').on('click', function(event){
        event.preventDefault();

        var href = $(this).attr('href');

        $('#orderModal #orderRef').attr('href', href);

        $('#orderModal').modal();

    })
});