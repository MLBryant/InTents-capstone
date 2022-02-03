const campgroundDiv = document.getElementById("campgroundDiv")
const commentDiv = document.getElementById('commentDiv')
const logoutButton = document.getElementById("campgroundLogoutButton")
const homeButton = document.getElementById('homeButton')
const commentModal = document.getElementById('commentModal')
const addCommentForm = document.getElementById('addCommentForm')
const addCommentField = document.getElementById('addCommentField')
const commentCancelButton = document.getElementById('commentCancel')
const submitButton = document.getElementById('submitButton')

let myStorage = window.localStorage
let commentId

const getCampground = () => {
    axios.get(`HTTP://localhost:8080/api/v1/campgrounds/${myStorage.campgroundFacilityId}`)
        .then(res => {
            console.log(res.data)
            let {facilityId, facilityName, state, sitesWithPetsAllowed, sitesWithAmps, sitesWithWaterHookup, sitesWithSewerHookup, faciltyPhoto} = res.data
            if (faciltyPhoto == "/images/nophoto.jpg") {
                faciltyPhoto = "/webphotos/KY/pid91833/0/80x53.jpg"
            }
            let campgroundCard = document.createElement('div')
            campgroundCard.classList.add('campgroundCard')
            campgroundCard.innerHTML = `    <div class = 'campgroundCardDiv'>
                                            <img class = 'campgroundPhoto' src = "http://www.reserveamerica.com${faciltyPhoto}" >
                                            <div class = 'campgroundTextDiv'><h1 class = 'campgroundText'>${facilityName}</h1>
                                            <h2 class = 'campgroundText'>${state}</h2>
                                            <h4 class = 'campgroundText'>pets allowed ${sitesWithPetsAllowed}</h4>
                                            <h4 class = 'campgroundText'>sites with amps ${sitesWithAmps}</h4>
                                            <h4 class = 'campgroundText'>sites with water hookups ${sitesWithWaterHookup}</h4>
                                            <h4 class = 'campgroundText'>sites with sewer hookups ${sitesWithSewerHookup}</h4></div>
                                            </div>`
            const campgroundButtonsDiv = document.createElement('div')
            campgroundButtonsDiv.classList.add('campgroundButtonsDiv')
            const commentButton = document.createElement('button')
            commentButton.classList.add('campgroundButton')
            commentButton.textContent = 'Add a Comment'
            commentButton.addEventListener('click', openCommentModal)
            campgroundButtonsDiv.appendChild(commentButton)
            campgroundCard.appendChild(campgroundButtonsDiv)
            campgroundDiv.appendChild(campgroundCard)
        })
}

const addComment = event => {
    event.preventDefault()
    const comment = addCommentField.value
    const commentRequest = {
        "user": {"id": myStorage.id},
        "campground": {"facilityId": myStorage.campgroundFacilityId},
        "comment": comment
    }
    axios.post("HTTP://localhost:8080/api/v1/comments", commentRequest)
        .then(res => {
            addCommentField.value = ''
            commentModal.style.display = 'none'
            addCommentForm.removeEventListener('submit', addComment)
            commentCancelButton.removeEventListener('click', cancelComment)
            getCampgroundComments()
        })
}

const editComment = event => {
    event.preventDefault()
    let comment = addCommentField.value
    let editRequest = {
        "id": commentId,
        "user": {},
        "campground": {},
        "comment": comment
    }
    console.log(editRequest)
    axios.put("HTTP://localhost:8080/api/v1/comments", editRequest)
        .then(res => {
            addCommentField.value = ''
            commentModal.style.display = 'none'
            addCommentForm.removeEventListener('submit', addComment)
            commentCancelButton.removeEventListener('click', cancelComment)
            submitButton.value = 'Add Comment'
            getCampgroundComments()
            commentId = null
        })
        .catch((error) => console.log( error.response.request._response ) );
}

const getCampgroundComments = () => {
    axios.get(`HTTP://localhost:8080/api/v1/comments/${myStorage.campgroundFacilityId}`)
        .then(res => {
            commentDiv.innerHTML = ''
            console.log(res.data)
            res.data.forEach((elem, i) => {
                let commentId = elem[0]
                let userId = elem[1]
                let userName = elem[2]
                let comment = elem[3]
                console.log(commentId)
                const commentCard = document.createElement('div')
                commentCard.classList.add('commentCard')
                commentCard.innerHTML = `
                                        <div class = 'commentCardDiv'>
                                        <h5>${userName}:</h5>
                                        <div class = 'userCommentDiv'>
                                        <p class = 'comment'>${comment}</p>
                                        </div>
                                        </div>`
                if (userId == myStorage.id) {
                    const editButtonsDiv = document.createElement('div')
                    editButtonsDiv.classList.add('editButtonsDiv')
                    const editCommentButton = document.createElement('button')
                    editCommentButton.textContent = "Edit"
                    editCommentButton.id = commentId
                    editCommentButton.addEventListener('click', openEditCommentModal)
                    editButtonsDiv.appendChild(editCommentButton)
                    const deleteCommentButton = document.createElement('button')
                    deleteCommentButton.id = commentId
                    deleteCommentButton.textContent = 'Delete'
                    deleteCommentButton.addEventListener('click', deleteComment)
                    editButtonsDiv.appendChild(deleteCommentButton)
                    commentCard.appendChild(editButtonsDiv)
                }
                commentDiv.appendChild(commentCard)
            })
        })
}

const deleteComment = event => {
    event.preventDefault()
    axios.delete(`HTTP://localhost:8080/api/v1/comments/${event.target.id}`)
        .then(res => {
            getCampgroundComments()
        })

}

const openCommentModal = event => {
    event.preventDefault()
    console.log(event.target.id)
    commentModal.style.display = 'block'
    addCommentForm.addEventListener('submit', addComment)
    commentCancelButton.addEventListener('click', cancelComment)
}

const cancelComment = event => {
    event.preventDefault()
    addCommentField.value = ''
    submitButton.value = 'Add Comment'
    commentModal.style.display = 'none'
    addCommentForm.removeEventListener('click', addComment)
    commentCancelButton.removeEventListener('click', cancelComment)
}

const openEditCommentModal = event => {
    event.preventDefault()
    console.log(event.target.id)
    commentId = event.target.id
    submitButton.value = 'Edit Comment'
    commentModal.style.display = 'block'
    addCommentForm.addEventListener('submit', editComment)
    commentCancelButton.addEventListener('click', cancelComment)
}

const logout = event => {
    event.preventDefault()
    myStorage.clear()
    window.location.href = "HTTP://localhost:8080/login.html"
}

const goHome = event => {
    event.preventDefault()
    window.location.href = 'HTTP://localhost:8080/home.html'
}

const checkLoggedIn = () => {
    if (!myStorage.id) {
        window.location.href = "HTTP://localhost:8080/login.html"
    }
}

checkLoggedIn()
getCampground()
getCampgroundComments()

logoutButton.addEventListener('click', logout)
homeButton.addEventListener('click', goHome)