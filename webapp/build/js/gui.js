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
		var serviceId = $(this).next().next().val();
		var user = $(this).next().next(); //User to upade from the admin page
		//Initialisation
		$('input[name="mode"]').val("insert");
		$('input[name="type"]').val("");
		$('textarea[name="description"]').text("");
		if ($(this).attr("id")!="add-need" && $(this).attr("id")!="add-offer" && $(this).attr("id")!="add-user") {
			$('input[name="type"]').val($("#list-elt-"+serviceId+" h3").text());
			$('textarea[name="description"]').text($("#list-elt-"+serviceId+" p").text());
			$('input[name="mode"]').val("update");
			$('#serviceId').val(serviceId);
		}
		//The admin zone user manager
		//Initialization when it insert mode
		$('input[name="lastName"]').val("");
		$('input[name="firstName"]').val("");
		$('input[name="email"]').val("");
		$('input[name="password"]').val("");
		if (user.attr("id")=="tmpId_"+user.val()) {
			$('input[name="lastName"]').val($('#tmpName_'+user.val()).val());
			$('input[name="firstName"]').val($('#tmpFirstName_'+user.val()).val());
			$('input[name="email"]').val($('#tmpEmail_'+user.val()).val());
			$('input[name="password"]').val($('#tmpPassword_'+user.val()).val());
			$('#currentUserId').val(user.val()); //id of the user to update
			$('input[name="mode"]').val("update");
		}
		//var classs = $(".user-modal-link")[1].nodeName;
		//console.log(class)
		//if ($(this).attr("class")==)
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
            window.open("delete?id="+userId+"&status="+status);
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
	{ title: "Serrurerie" },
	{ title: "volets roulants" },
	{ title: "terrassement" },
	{ title: "Pose – Réparation" },
	{ title: "Ouverture de Porte" },
	{ title: "Ebénisterie - cuisiniste - agencement – parquet" },
	{ title: "Spécialiste de la piscine" },
	{ title: "entetien" },
	{ title: "dépannage" },
	{ title: "Serrurerie" },
	{ title: "Métallerie-ferronnerie" },
	{ title: "portes blindées" },
	{ title: "grilles" },
	{ title: "rampes" },
	{ title: "portails automatismes" },
	{ title: "coffre-forts"},
	{ title: "menuiserie métallique et PVC." },
	{ title: "Maintenance" },
	{ title: "Dépannage" },
	{ title: "Chaudières Fioul/Gaz" },
	{ title: "Electricité Générale" },
	{ title: "Climatisation réversible" },
	{ title: "Alarmes" },
	{ title: "Antennes" },
	{ title: "Automatisme de portail" },
	{ title: "Poste MT BT" },
	{ title: "Professionnel conventionnés VIVRELEC" }
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
