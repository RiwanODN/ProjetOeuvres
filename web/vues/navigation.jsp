<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Médiathèque de POLYTECH</a>
            </div>
            <p class="navbar-text">Gestion de l'exposition 2016</p>
            <ul class="nav navbar-nav">
                <li><a href="index.jsp"> <span class="glyphicon glyphicon-home"></span> Accueil</a></li>
                <c:if test="${sessionScope.id == null }">
                <li class="dropdown">
                    <a class="nav navbar-nav navbar-right"  href="ServletControleur?action=login">
                        <span class="glyphicon glyphicon-user"></span>
                        Se Connecter
                        <span class="caret"></span>
                    </a>
                    </c:if>
                    <c:if test="${sessionScope.id > 0  }">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                        Adhérents
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li><a href="ServletControleur?action=ajouterAdherent"> <span class="glyphicon glyphicon-plus"></span> Ajout Adhérent</a></li>
                        <li><a href="ServletControleur?action=listerAdherent"><span class="glyphicon glyphicon-th-list"></span> Lister les adhérents</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-book"></span>
                        Oeuvres
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li><a href="ServletControleur?action=ajouterOeuvre"> <span class="glyphicon glyphicon-plus"></span> Ajout Oeuvre</a></li>
                        <li><a href="ServletControleur?action=listerOeuvre"><span class="glyphicon glyphicon-th-list"></span> Lister les oeuvres</a></li>
                    </ul>
                </li>
                <li><a href="javascript:fermer()"><span class="glyphicon glyphicon-log-out"></span> Quitter</a></li>
                </c:if>

            </ul>
        </div>
    </nav>
</div>