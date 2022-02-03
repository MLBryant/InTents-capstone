const campgroundDiv = document.getElementById("campgroundDiv")
const commentDiv = document.getElementById('commentDiv')
const logoutButton = document.getElementById("logoutButton")
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
            campgroundCard.innerHTML = `
                                            <img src = "http://www.reserveamerica.com${faciltyPhoto}" >
                                            <div><h2>${facilityName}</h2>
                                            <h3>${state}</h3>
                                            <h4>pets allowed ${sitesWithPetsAllowed}</h4>
                                            <h4>sites with amps ${sitesWithAmps}</h4>
                                            <h4>sites with water hookups ${sitesWithWaterHookup}</h4>
                                            <h4>sites with sewer hookups ${sitesWithSewerHookup}</h4></div>`
            const commentButton = document.createElement('button')
            commentButton.textContent = 'Add a Comment'
            commentButton.addEventListener('click', openCommentModal)
            campgroundCard.appendChild(commentButton)
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
                commentCard.innerHTML = `
                                        <h5>${userName}:</h5>
                                        <p>${comment}</p>`
                if (userId == myStorage.id) {
                    const editCommentButton = document.createElement('button')
                    editCommentButton.textContent = "Edit"
                    editCommentButton.id = commentId
                    editCommentButton.addEventListener('click', openEditCommentModal)
                    commentCard.appendChild(editCommentButton)
                    const deleteCommentButton = document.createElement('button')
                    deleteCommentButton.id = commentId
                    deleteCommentButton.textContent = 'Delete'
                    deleteCommentButton.addEventListener('click', deleteComment)
                    commentCard.appendChild(deleteCommentButton)
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

getCampground()
getCampgroundComments()

logoutButton.addEventListener('click', logout)