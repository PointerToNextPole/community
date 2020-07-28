function post() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if(response.code == 200){
                $("#comment_section").hide();
            }else{
                alert(response.message);
            }
            console.log(response);
        },
        dataType: "json"
    });
}