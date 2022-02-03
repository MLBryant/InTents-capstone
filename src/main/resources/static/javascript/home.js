const stateSelect = document.getElementById("stateSelect")
const searchButton = document.getElementById("searchButton")
const logoutButton = document.getElementById("homeLogoutButton")
const searchColumn = document.getElementById("searchColumn")
const listColumn = document.getElementById("listColumn")

let campgroundArray = []
let userCampgroundArray = []
let myStorage = window.localStorage

const search = event => {
    event.preventDefault()
    const state = stateSelect.value
    const searchRequest = {
        "state": state
    }
    axios.post("HTTP://localhost:8080/api/v1/search", searchRequest)
        .then(res => {
            searchColumn.innerHTML = ''
            console.log(res.data)
            campgroundArray = []
            res.data.forEach((elem, i) => {
                let {facilityId, facilityName, state, sitesWithPetsAllowed, sitesWithAmps, sitesWithWaterHookup, sitesWithSewerHookup, faciltyPhoto} = elem
                if (faciltyPhoto == "/images/nophoto.jpg") {
                    faciltyPhoto = "/webphotos/KY/pid91833/0/80x53.jpg"
                }
                let campground = {
                    facilityId: facilityId,
                    facilityName: facilityName,
                    state: state,
                    sitesWithPetsAllowed: sitesWithPetsAllowed,
                    sitesWithAmps: sitesWithAmps,
                    sitesWithWaterHookup: sitesWithWaterHookup,
                    sitesWithSewerHookup: sitesWithSewerHookup,
                    faciltyPhoto: faciltyPhoto
                }
                let campgroundCard = document.createElement('div')
                campgroundCard.classList.add('campgroundSearchCard')
                campgroundCard.innerHTML = `<div class = 'searchCard'>
                                            <img class = 'searchPhoto' src = "http://www.reserveamerica.com${faciltyPhoto}" >
                                            <div class = 'searchTextDiv'><h2 class = 'searchText'>${facilityName}</h2>
                                            <h3 class = 'searchText'>${state}</h3>
                                            <h4 class = 'searchText'>pets allowed ${sitesWithPetsAllowed}</h4>
                                            <h4 class = 'searchText'>sites with amps ${sitesWithAmps}</h4>
                                            <h4 class = 'searchText'>sites with water hookups ${sitesWithWaterHookup}</h4>
                                            <h4 class = 'searchText'>sites with sewer hookups ${sitesWithSewerHookup}</h4></div>
                                            </div>`
                const addCampgroundButton = document.createElement('button')
                addCampgroundButton.id = i
                addCampgroundButton.classList.add('searchAddButton')
                addCampgroundButton.textContent = "Add to List"
                addCampgroundButton.addEventListener("click", addCampground)
                campgroundCard.appendChild(addCampgroundButton)
                searchColumn.appendChild(campgroundCard)
                campgroundArray.push(campground)
            })
            console.log(campgroundArray)
        })
}

const getUserCampgrounds = () => {
    axios.get(`HTTP://localhost:8080/api/v1/usercampground/${myStorage.id}`)
        .then(res => {
            userCampgroundArray = []
            listColumn.innerHTML = ''
            listColumn.innerHTML = `<h2>${myStorage.userName}'s List`
            console.log(res.data)
            res.data.forEach((elem, i) => {
                console.log(elem)
                facilityName = elem[2]
                state = elem[3]
                faciltyPhoto = elem[8]
                if (faciltyPhoto == "/images/nophoto.jpg") {
                    faciltyPhoto = "/webphotos/KY/pid91833/0/80x53.jpg"
                }
                console.log(facilityName, state, faciltyPhoto)
                let userCampgroundCard = document.createElement("div")
                userCampgroundCard.classList.add('userCampgroundCard')
                userCampgroundCard.innerHTML = `
                                                <img class = 'userCampgroundPhoto' src = 'HTTP://www.reserveamerica.com${faciltyPhoto}'>
                                                <div class = 'userCampgroundTextDiv'><h3 class = 'userCampgroundText'>${facilityName}</h3>
                                                <h4 class = 'userCampgroundText'>${state}</h4></div>`
                const userCampgroundButtonsDiv = document.createElement('div')
                userCampgroundButtonsDiv.classList.add('userCampgroundButtonsDiv')
                const viewButton = document.createElement('button')
                viewButton.id = i
                viewButton.classList.add('userCampgroundButton')
                viewButton.textContent = 'View'
                viewButton.addEventListener('click', viewUserCampground)
                userCampgroundButtonsDiv.appendChild(viewButton)
                const removeButton = document.createElement('button')
                removeButton.id = elem[1]
                removeButton.classList.add('userCampgroundButton')
                removeButton.textContent = "Remove"
                removeButton.addEventListener("click", removeUserCampground)
                userCampgroundButtonsDiv.appendChild(removeButton)
                userCampgroundCard.appendChild(userCampgroundButtonsDiv)
                listColumn.appendChild(userCampgroundCard)
                let userCampground = {
                    facilityName: facilityName,
                    state: state,
                    faciltyPhoto: faciltyPhoto,
                    facilityId: elem[1]
                }
                userCampgroundArray.push(userCampground)
            })
        })
}

const addCampground = event => {
    event.preventDefault()
    axios.post("HTTP://localhost:8080/api/v1/campgrounds", campgroundArray[event.target.id])
        .then(res => {
            console.log(res.data)
            axios.post(`HTTP://localhost:8080/api/v1/usercampground/${myStorage.id}/${campgroundArray[event.target.id].facilityId}`)
            .then(res => {
                getUserCampgrounds()
                })
        })
}

const removeUserCampground = event => {
    event.preventDefault()
    axios.delete(`HTTP://localhost:8080/api/v1/usercampground/remove/${myStorage.id}/${event.target.id}`)
        .then(res => {
            console.log(res.data)
            getUserCampgrounds()
        })
}

const viewUserCampground = event => {
    event.preventDefault()
    myStorage.setItem("campgroundFacilityId", userCampgroundArray[event.target.id].facilityId)
    window.location.href = "HTTP://localhost:8080/campground.html"
}

const logout = event => {
    event.preventDefault()
    myStorage.clear()
    window.location.href = "HTTP://localhost:8080/login.html"
}

const checkLoggedIn = () => {
    if (!myStorage.id) {
        window.location.href = "HTTP://localhost:8080/login.html"
    }
}

checkLoggedIn()
getUserCampgrounds()

searchButton.addEventListener("click", search)
logoutButton.addEventListener('click', logout)