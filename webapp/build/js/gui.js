// Gestion de la scrollbar
$( "body" ).mCustomScrollbar({
    scrollInertia: 0
});
// Surbrillance de la cellule de la personne qui rend service dans le tableau
$(".table tbody > tr:first-child").css("background","rgba(181, 230, 29, 0.8)");

// Gestion des pop-ups
showModal("sign-in-link", "sign-in");
showModal("sign-up-link", "sign-up");
showModal("need-modal-link", "need-modal");
showModal("offer-modal-link", "offer-modal");
showModal("user-modal-link", "user-modal");

function showModal(classLink, idModal) {
    $( "." + classLink ).click(function() {
        var serviceId = $(this).next().next().val();
        var user = $(this).next().next(); //User to upade from the admin page
        //Initialisation
        initializeElements();
        $('input[name="mode"]').val("insert");
        if ($(this).attr("id")!="add-need" && $(this).attr("id")!="add-offer" && $(this).attr("id")!="add-user") {
            $('input[name="type"]').val($("#list-elt-"+serviceId+" h3").text());
            $('textarea[name="description"]').text($("#list-elt-"+serviceId+" p").text());
            $('input[name="mode"]').val("update");
            $('#serviceId').val(serviceId);
        }
        if (user.attr("id")=="tmpId_"+user.val()) {
            $('input[name="lastName"]').val($('#tmpName_'+user.val()).val());
            $('input[name="firstName"]').val($('#tmpFirstName_'+user.val()).val());
            $('input[name="email"]').val($('#tmpEmail_'+user.val()).val());
            $('input[name="password"]').val($('#tmpPassword_'+user.val()).val());
            $('#currentUserId').val(user.val()); //id of the user to update
            $('input[name="mode"]').val("update");
        }
        var href = $(this).attr("href"); //CurrentButton href
        if (href != "logout" && href != "accueil") { // Gestion de la page d'accueil
            $('#' + idModal).modal('setting', {
                'onApprove': function() {
                    $( "#" + idModal + " form" ).trigger( "submit" );
                    return false; // On bloque la fermeture de la modale
                },
            }).modal('show');
        }
    });
}

function initializeElements() {

    $("input").each(function(){
        var type = $(this).attr("type");
        if(type == "text" || type == "password")
            $(this).val("");
    });
    $("textarea").each(function(){
        var tagName = $(this).prop("tagName").toLowerCase();
        if(tagName == "textarea")
            $(this).text("");
    });
}

// Pop-up de déconnexion
showAlert("logout");
showAlert("admin");

function showAlert(id) {

    if (id == "admin" && $( "#admin-modal" ).length == 0) {
        // On ne fait rien...
    } else {
        $( "#" + id ).click(function(e) {
            e.preventDefault();
            $('#'+id+'-modal').modal('setting', {
                'onApprove': function() {
                    location.href = id;
                }
            }).modal('show');
        });
    }
}

// Formulaire de modification de profil
$( "#edit-profile-button, button[type='reset']" ).click(function() {
    $( "#edit-profile-form" ).slideToggle();
});

// Affichage des détails pour les besoins et les offres
$( ".list-elt-button" ).each(function() {
    var idLink = $( this ).attr( "id" ); // id du lien : list-elt-1-link
    var idElement = idLink.substr(0, idLink.lastIndexOf("-")); // résultat attendu : list-elt-1
    $( "#" + idLink ).click(function() {
        console.log($("#" + idLink).next().next().next().val());
        var idToSend = $("#" + idLink).next().next().next().val();

        if ($( "#" + idLink ).text() == "Afficher") {
            $( "#" + idLink ).text( "Masquer" );
        } else {
            $( "#" + idLink ).text( "Afficher" );
        }
        $( "#" + idElement ).slideToggle();
    });
});

// Affichage d'une page de profil depuis la zone d'administration
$( ".list-elt-link" ).click(function() {
    var userId = $(this).next().next().next().val();
    console.log(userId);
    window.open("profil?id="+userId, "_blank");
});

// Gestion de la suppression d'un besoin, d'une offre, et d'un utilisateur
$( ".delete-btn" ).click(function() {
    var userId = $(this).next().val();
    var currentElt = $( this );
    $('.delete-list-elt').modal('setting', {
        'onApprove': function() {
            currentElt.parent().parent().remove();
            $(".list-elt" ).slideUp();
            $(".list-elt-button" ).each(function() {
                $( this ).text("Afficher");
            });
            var status = $('input[name="needOrOffer"]').val();
            location.href = "delete?id="+userId+"&status="+status;
        }
    }).modal('show');
});

// Affichage des cycles
$( "#show-cycles" ).click(function() {
    $( "#cycles-display" ).fadeIn();
});

// Types de services
var content = [
    { title: "Alarme & Sécurité" },
    { title: "Aménagement de cuisine" },
    { title: "Aménagement de salle de bains" },
    { title: "Architecture" },
    { title: "Charpenterie" },
    { title: "Chauffage & Climatisation" },
    { title: "Décoration" },
    { title: "Diagnostic immobilier" },
    { title: "Électricité" },
    { title: "Isolation" },
    { title: "Maçonnerie" },
    { title: "Menuiserie & Ébénisterie" },
    { title: "Métallerie & Ferronnerie" },
    { title: "Paysage/Jardin" },
    { title: "Peinture" },
    { title: "Piscine" },
    { title: "Plomberie" },
    { title: "Serrurerie" },
    { title: "Terrassement" },
    { title: "Véranda" },
    // etc.
];

// Gestion de l'autocomplétion pour les types de services
$('.ui.search')
  .search({
    source: content
  })
;

// Prévisualisation des images lors d'un upload
$( "#file" ).change(function(e) {

  if (typeof (FileReader) != "undefined") {
    var selectedImage = $( "#selected-image" );
    selectedImage.empty();
    var reader = new FileReader();
    reader.onload = function (e) {
        $("<img />", {
            "src": e.target.result
        }).appendTo(selectedImage);
    }
    selectedImage.show();
    reader.readAsDataURL($( this )[0].files[0]);
  } else {
      console.log("Ce navigateur ne gère pas FileReader.");
  }

});
