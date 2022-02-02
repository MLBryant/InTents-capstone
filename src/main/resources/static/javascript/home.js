const stateSelect = document.getElementById("stateSelect")
const searchButton = document.getElementById("searchButton")
const logoutButton = document.getElementById("logoutButton")

const campgroundIds = []

const search = event => {
    event.preventDefault()
    const state = stateSelect.value
    const searchRequest = {
        "state": state
    }
    axios.post("HTTP://localhost:8080/api/v1/search", searchRequest)
        .then(res => {
            const searchColumn = document.getElementById("searchColumn")
            res.data.forEach(elem => {
                let {facilityId, facilityName, state, sitesWithPetsAllowed, sitesWithAmps, sitesWithWaterHookup, sitesWithSewerHookup, faciltyPhoto} = elem
                let campgroundCard = document.createElement('div')
                campgroundCard.innerHTML = `
                                            <img src = "http://www.reserveamerica.com${faciltyPhoto}" >
                                            <h2>${facilityName}</h2>
                                            <h3>${state}</h3>
                                            <h4>pets allowed ${sitesWithPetsAllowed}</h4>
                                            <h4>sites with amps ${sitesWithAmps}</h4>
                                            <h4>sites with water hookups ${sitesWithWaterHookup}</h4>
                                            <h4>sites with sewer hookups ${sitesWithSewerHookup}</h4>
                                            `
                searchColumn.appendChild(campgroundCard)
                campgroundIds.push(facilityId)
            })
        })
}

searchButton.addEventListener("click", search)