function ConfirmDelete() {
    var answer = confirm("Are You Sure Want to Delete?")
    return answer == true ? true : false
}
      function toggleLabel(){
    $.ajax("<s:url action='mainMenuLabelToggle'/>");
    document.location.reload(true);
  }
  
