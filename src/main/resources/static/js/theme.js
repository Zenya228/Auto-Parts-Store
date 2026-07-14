(function () {
    var toggle = document.getElementById('theme-toggle');
    if (!toggle) {
        return;
    }

    toggle.addEventListener('click', function () {
        var current = document.documentElement.getAttribute('data-theme') === 'light' ? 'light' : 'dark';
        var next = current === 'dark' ? 'light' : 'dark';
        document.documentElement.setAttribute('data-theme', next);
        localStorage.setItem('theme', next);
    });
})();
