document.addEventListener('DOMContentLoaded', function() {
    const articles = document.querySelectorAll('.article-link');

    articles.forEach(article => {
        article.addEventListener('click', function(event) {
            event.preventDefault();
            const url = this.getAttribute('data-url');
            window.open(url, '_blank', 'width=800,height=600');

            // Save to cookie
            let recentArticles = getCookie('recentArticles');
            recentArticles = recentArticles ? JSON.parse(recentArticles) : [];
            recentArticles.unshift(url);
            if (recentArticles.length > 5) {
                recentArticles.pop();
            }
            setCookie('recentArticles', JSON.stringify(recentArticles), 7);
        });
    });

    function setCookie(name, value, days) {
        const date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        const expires = "expires=" + date.toUTCString();
        document.cookie = name + "=" + value + ";" + expires + ";path=/";
    }

    function getCookie(name) {
        const nameEQ = name + "=";
        const ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }
});