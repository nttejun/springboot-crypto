<%--
  Created by IntelliJ IDEA.
  User: wjjeong
  Date: 27/09/2019
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="testdata">
Crypto text : ${testdata}
</div>
<div id="result">
</div>
</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/hmac-sha256.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/enc-base64.min.js"></script>

<script>

    function getUsername(){
        var DK = pbkdf2('passwd', 'salt', 1);
        var usernameString = hexToString(hexXorAlgorithm("${testdata}", DK.toString()));
        return usernameString;
    }

    function pbkdf2(password, salt, iteration) {
        var key = CryptoJS.PBKDF2(password, salt, {
            iterations: iteration,
            keySize: 256 / 32,
            hasher: CryptoJS.algo.SHA256
        });
        return key;
    }

    function hexXorAlgorithm(encryptData, pbkdf2Key) {
        var res = "",
            encryptDataIndex = encryptData.length,
            pbkdf2KeyIndex = pbkdf2Key.length;
        while (encryptDataIndex-->0 && pbkdf2KeyIndex-->0)
            res = (parseInt(encryptData.charAt(encryptDataIndex), 16) ^ parseInt(pbkdf2Key.charAt(pbkdf2KeyIndex), 16)).toString(16) + res;
        return res;
    }

    function hexToString(hex) {
        var hexToString = '';
        for (var i = 0; i < hex.length; i += 2) {
            hexToString += String.fromCharCode(parseInt(hex.substr(i, 2), 16))
        }
        return decodeURIComponent(escape(hexToString));
    }

    alert(getUsername());

</script>
</html>
