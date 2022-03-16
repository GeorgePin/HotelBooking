function ConfirmDelete() {
    const answer = confirm("Are you sure?")
    return answer
}
function updateEndDate() {
    const startDtValue = document.getElementById("startDate").value
    document.getElementById("endDate").setAttribute("min", startDtValue)
}
