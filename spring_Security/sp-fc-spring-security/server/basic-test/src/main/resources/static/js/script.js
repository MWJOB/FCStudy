// script.js
document.addEventListener("DOMContentLoaded", function() {
    // dummy 버튼 클릭 시 alert 메시지 출력
    const dummyButtons = document.querySelectorAll(".dummy-btn");
    dummyButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            alert("이 버튼은 아직 기능이 구현되지 않았습니다.");
        });
    });
});
