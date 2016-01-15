// Gestion de la scrollbar
$( "body" ).mCustomScrollbar({
    scrollInertia: 0
});

// Gestion des pop-ups
showModal("sign-in-link", "sign-in");
showModal("sign-up-link", "sign-up");
showModal("need-modal-link", "need-modal");
showModal("offer-modal-link", "offer-modal");
showModal("user-modal-link", "user-modal");

function showModal(classLink, idModal) {
    $( "." + classLink ).click(function() {
		var needId = $(this).next().next().val();
		if ($(this).attr("id")!="add-need") {
			$('input[name="type"]').val($("#list-elt-"+needId+" h3").text());
			$('textarea[name="description"]').text($("#list-elt-"+needId+" p").text());
			//$('input[name="deadline"]').val($("list-elt-"+needId+" h3").text());
			$('input[name="mode"]').val("update");
			$('#serviceId').val(needId);
		}
        $('#' + idModal).modal('setting', {
            'onApprove': function() {
                $( "#" + idModal + " form" ).trigger( "submit" );
                return false; // On bloque la fermeture de la modale
            },
        }).modal('show');
    });
}

submitModal("sign-in");
submitModal("sign-up");
submitModal("need-modal");
submitModal("offer-modal");
submitModal("user-modal");

function submitModal(id) {
    $( "#" + id + " form" ).submit(function() {
        if ($( this ).form('is valid')) {
            switch (id) {
                case "sign-in":
                case "sign-up":
                    // location.href = "accueil";
                    break;
                case "need-modal":
                    location.href = "besoins";
                    break;
                case "offer-modal":
                    location.href = "offres";
                    break;
                case "user-modal":
                    location.href = "admin";
                    break; 
            }
            // On bloque la redirection par défaut du navigateur
            // return false;
        }
    });
}

// Menu vertical
//~ $( ".ui.vertical.menu .item" ).click(function() {
    //~ $( this ).addClass( "active" );
    //~ $( this ).siblings( ".item" ).removeClass( "active" );
//~ });

// Déconnexion
/*$( "#logout" ).click(function() {
    $('#logout-modal').modal('setting', {
        'onApprove': function() {
            location.href = "index.jsp";
        }
    }).modal('show');
});*/

// Formulaire de modification de profil
$( "#edit-profile-button" ).click(function() {
    $( "#edit-profile-form" ).slideToggle();
});

/*$( "#edit-profile-form" ).submit(function(e) {
    e.preventDefault();
    if ($( this ).form('is valid')) {
        location.href = "";
    }
});*/

// Affichage des détails pour les besoins et les offres
$( ".list-elt-button" ).each(function() {
    var idLink = $( this ).attr( "id" ); // id du lien : list-elt-1-link
    var idElement = idLink.substr(0, 10); // résultat attendu : list-elt-1
    $( "#" + idLink ).click(function() {
		console.log($("#" + idLink).next().next().next().val());
		var idToSend = $("#" + idLink).next().next().next().val();
		//Récupération du besoin à afficher via ajax
		//performAjax("http://localhost:8080/e-artisan/besoins/",{id:idToSend},"GET",parseNeeds);
		
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
    window.open("profil", "_blank");
});

// Gestion de la suppression d'un besoin, d'une offre, et d'un utilisateur
$( ".delete-btn" ).click(function() {
	console.log($(this).next().val());
    var currentElt = $( this );
    $('.delete-list-elt').modal('setting', {
        'onApprove': function() {
            currentElt.parent().parent().remove();
            $( ".list-elt" ).slideUp();
            $( ".list-elt-button" ).each(function() {
                $( this ).text( "Afficher" );
            });
        }
    }).modal('show');
});

// Affichage des cycles
$( "#show-cycles" ).click(function() {
    $( "#main-section table" ).fadeIn();
});

// Types de services
var content = [
  { title: 'Charpenterie' },
  { title: 'Maçonnerie' },
  { title: 'Paysage/Jardin' },
  // etc.
];

// Gestion de l'autocomplétion pour les types de services
$('.ui.search')
  .search({
    source: content
  })
;
function parseNeeds(result) {
	console.log(result);
}
//AJax
function performAjax(url,data,type,callback) {
	$.ajax({
		  url: url,
		  data : data,
		  type: type,
		  dataType: "json"
		}).success(callback);
}
