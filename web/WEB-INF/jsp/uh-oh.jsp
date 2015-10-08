<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/jsp/inc/html-head.jsp" %>
<body>
<div id="wrapper">
    <%@include file="/WEB-INF/jsp/inc/header.jsp" %>
    <main id="main" role="main">
        <section class="error" style="background-image:url(images/bg-error.jpg);">
            <span class="overlay"></span>
            <div class="text-wrap">
                <div class="text-holder">
                    <h1>Something is definitely wrong!</h1>
                    <p>An error occured and you’ve ended up here.<br> You’ll probably fix it somehow.</p>
                    <a href="#" class="btn">Back <span class="thin">to</span> Homepage</a>
                </div>
            </div>
        </section>
    </main>
</div>
<%@include file="/WEB-INF/jsp/inc/scripts.jsp" %>
</body>
</html>