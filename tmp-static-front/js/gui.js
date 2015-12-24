// Menu vertical
//~ $( ".ui.vertical.menu .item" ).click(function() {
    //~ $( this ).addClass( "active" );
    //~ $( this ).siblings( ".item" ).removeClass( "active" );
//~ });

// Déconnexion
$( "#logout" ).click(function() {
    $('#logout-modal').modal('show');
});

// Formulaire de modification de profil
$( "#edit-profile-button" ).click(function() {
    $( "#edit-profile-form" ).slideToggle();
});
