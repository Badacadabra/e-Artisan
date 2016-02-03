// Validation du formulaire de connexion
$('#sign-in form')
  .form({
    fields: {
      login: {
        identifier: 'login',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer votre identifiant.'
          }
        ]
      },
      password: {
        identifier: 'password',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer votre mot de passe.'
          }
        ]
      }
    },
    inline : true,
  })
;

// Validation des formulaires d'inscription et de création/modification d'utilisateur par l'admin
$('#sign-up form, #user-modal form')
  .form({
    fields: {
      lastName: {
        identifier: 'lastName',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un nom.'
          },
          {
            type   : 'regExp[/^[a-zA-Z-àâçéèêëîïôöûùüæœÀÂÇÉÈÊËÎÏÔÖÛÙÜÆŒ ]{2,30}$/g]',
            prompt : 'Veuillez entrer un nom valide.'
          }
        ]
      },
      firstName: {
        identifier: 'firstName',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un prénom.'
          },
          {
            type   : 'regExp[/^[a-zA-Z-àâçéèêëîïôöûùüæœÀÂÇÉÈÊËÎÏÔÖÛÙÜÆŒ ]{2,30}$/g]',
            prompt : 'Veuillez entrer un prénom valide.'
          }
        ]
      },
      email: {
        identifier: 'email',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un e-mail.'
          },
          {
            type   : 'email',
            prompt : 'Veuillez entrer un e-mail valide.'
          }
        ]
      },
      password: {
        identifier: 'password',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un mot de passe.'
          },
          {
            type   : 'minLength[8]',
            prompt : 'Votre mot de passe doit contenir au moins {ruleValue} caractères'
          }
        ]
      },
    },
    inline : true,
  })
;

// Validation du formulaire de modification de profil
$('#edit-profile-form')
  .form({
    fields: {
      lastName: {
        identifier: 'lastName',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un nom.'
          },
          {
            type   : 'regExp[/^[a-zA-Z-àâçéèêëîïôöûùüæœÀÂÇÉÈÊËÎÏÔÖÛÙÜÆŒ ]{2,30}$/g]',
            prompt : 'Veuillez entrer un nom valide.'
          }
        ]
      },
      firstName: {
        identifier: 'firstName',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un prénom.'
          },
          {
            type   : 'regExp[/^[a-zA-Z-àâçéèêëîïôöûùüæœÀÂÇÉÈÊËÎÏÔÖÛÙÜÆŒ ]{2,30}$/g]',
            prompt : 'Veuillez entrer un prénom valide.'
          }
        ]
      },
      email: {
        identifier: 'email',
        optional: true,
        rules: [
          {
            type   : 'email',
            prompt : 'Veuillez entrer un e-mail valide.'
          }
        ]
      },
      password: {
        identifier: 'password',
        optional: true,
        rules: [
          {
            type   : 'minLength[8]',
            prompt : 'Votre mot de passe doit contenir au moins {ruleValue} caractères'
          }
        ]
      },
    },
    inline : true,
  })
;

// Validation des formulaires d'ajout/modification d'un besoin ou d'une offre
$('#need-modal form, #offer-modal form')
  .form({
    fields: {
      type: {
        identifier: 'type',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer un type de service.'
          },
          {
            type   : 'regExp[/^[a-zA-Z0-9-\/&àâçéèêëîïôöûùüæœÀÂÇÉÈÊËÎÏÔÖÛÙÜÆŒ ]{2,30}$/g]',
            prompt : 'Veuillez choisir un service valide.'
          }
        ]
      },
      description: {
        identifier: 'description',
        rules: [
          {
            type   : 'empty',
            prompt : 'Veuillez entrer une description du service.'
          },
          {
            type   : 'minLength[30]',
            prompt : 'Votre description doit contenir au moins {ruleValue} caractères.'
          }
        ]
      }
    },
    inline : true,
  })
;
