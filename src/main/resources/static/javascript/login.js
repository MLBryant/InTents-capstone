const usernameInput = document.getElementById("username-input")
const passwordInput = document.getElementById("password-input")
const loginButton = document.getElementById("login-button")
const registerButton = document.getElementById("register-button")

let myStorage = window.localStorage

const register = event => {
    event.preventDefault()
    const username = usernameInput.value 
    const password = passwordInput.value
    const registerRequest = {
        userName: username,
        password: password
    }
    axios.post('http://localhost:8080/api/v1/users', registerRequest)
        .then(res => {
            console.log(res.data);
            if (res.data.id == null) {
                alert("Username already taken")
            } else {
                let {id, userName,} = res.data
                myStorage.setItem("id", id)
                myStorage.setItem("userName", userName)
                window.location.href = "http://localhost:8080/home.html"
            }
        })
}

const login = event => {
    event.preventDefault()
    const username = usernameInput.value
    const password = passwordInput.value
    const loginRequest = {
        userName: username,
        password: password
    }
    axios.post('http://localhost:8080/api/v1/users/login', loginRequest)
        .then(res => {
            console.log(res.data)
            if (res.data.id == null) {
                alert("Username or password incorrect!")
            } else {
                let {id, userName,} = res.data
                myStorage.setItem("id", id)
                myStorage.setItem("userName", userName)
                window.location.href = "http://localhost:8080/home.html"
            }
        })
}

registerButton.addEventListener('click', register)
loginButton.addEventListener('click', login)