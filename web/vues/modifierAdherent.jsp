<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modifier un adhérent </H1>
<form method="post" action="ServletControleur?action=modifierAdherent">
<div class="col-md-12 well well-md">
    <h1>Modifier Séjour</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Nom de l'adherent : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtnom" value=${item.nomAdherent} id="nom" class="form-control" min="0">
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prénom de l'adherent : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtprenom" value=${item.prenomAdherent} id="prenom" class="form-control" min="0">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Ville de l'adherent : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtville" value=${item.villeAdherent} id="ville" class="form-control" min="0">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
            Ajouter
        </button>

        <button type="button" class="btn btn-default btn-primary"
                onclick="{
                            window.location = '../index.jsp';
                        }">
            <span class="glyphicon glyphicon-remove"></span> Annuler

        </button>
    </div>
</div>
</form>
</body>
<%@include file="footer.jsp"%>
</html>