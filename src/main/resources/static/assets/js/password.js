function myFunction() {
    var x = document.getElementById("password");
    var y = document.getElementById("show1");
    var z = document.getElementById("hide1");

    if(x.type === 'password') {
        x.type = "text";
        y.style.display = "block";
        z.style.display = "none";
    } else {
        x.type = "password";
        y.style.display = "none";
        z.style.display = "block";
    }
}

function myFunction1() {
    var x = document.getElementById("matchingPassword");
    var y = document.getElementById("show");
    var z = document.getElementById("hide");

    if(x.type === 'password') {
        x.type = "text";
        y.style.display = "block";
        z.style.display = "none";
    } else {
        x.type = "password";
        y.style.display = "none";
        z.style.display = "block";
    }
}

function check() {
    var password = document.getElementById("password").value;
    var mpassword = document.getElementById("matchingPassword").value;
    if (password == mpassword) {
        document.getElementById("wrong").innerHTML = "";
        return true
    } else {
        document.getElementById("wrong").innerHTML = "Password didn't Match!"
        return false
    }
}