<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modifier une Oeuvre </H1>
<form method="post" action="ServletControleur?action=modifierOeuvre">
<div class="col-md-12 well well-md">
    <h1>Modifier Oeuvre</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Titre : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtnom" value='<c:out value="${oeuvre.getTitreOeuvrevente()}" />' id="nom" class="form-control" min="0">
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prix : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtprix" value='<c:out value="${oeuvre.getPrixOeuvrevente()}" />' id="prix" class="form-control" min="0">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Proprietaire : </label>
        <div class="col-md-3">
            <select name="txtprop" id="prop" class="form-control">
                <c:forEach items="${props}" var="item">
                    <option value='<c:out value="${item.getIdProprietaire()}" />'>
                        <c:out value="${item.getNomProprietaire()}" />
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
            Modifier
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