function copyToClipboard() {
            var text = document.getElementById("inputToken");
            text.select();
            text.setSelectionRange(0, 99999);
            navigator.clipboard.writeText(text.value);
        }