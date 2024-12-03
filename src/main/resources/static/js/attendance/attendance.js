let attendance_row = document.getElementById("attendance_list");
let attendance_empty = document.getElementById("attendance_empty");
let commute_container = document.getElementById("commute_container");

let today = new Date();
let year = today.getFullYear();
let month = String(today.getMonth() + 1).padStart(2,'0');
let day = String(today.getDate()).padStart(2,'0');
let now_date = (year + "-" + month + "-" + day);

let work_start = document.getElementsByClassName("work_start")[0];;
let work_end = document.getElementsByClassName("work_end")[0];

window.onload = () => {
    if (attendance_row.rows.length <= 0) {
        attendance_empty.style.display = "";
        work_end.style.display = "none";
    } else {
        attendance_empty.style.display = "none";
    }

    if (attendance_row.rows[0].cells[0].innerText === now_date) {
        if (!(attendance_row.rows[0].cells[2].innerText === "-")) {
            work_end.style.display = "none";

            const text = document.createElement("p");
            text.style.fontSize = "40px";
            text.style.fontWeight = "bold";
            text.innerText = "수고하셨습니다.";

            commute_container.appendChild(text);

            const significant_select_box = document.getElementById("significant");
            const significant_btn = document.getElementsByClassName("significant_btn")[0];

            significant_select_box.disabled = "disabled";
            significant_btn.disabled = "disabled";
            significant_btn.style.display = "none";
        }
        work_start.style.display = "none";
    } else {
        work_end.style.display = "none";
    }
}

work_start.addEventListener("click", () => {
    work_start.style.display = "none";
    work_end.style.display = "";

    const form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "go_to_work");

    // const emp_id_input = document.createElement("input");
    // emp_id_input.setAttribute("type", "hidden");
    // emp_id_input.setAttribute("name", "emp_id");
    // emp_id_input.setAttribute("value", emp_id)

    let hour = today.getHours();
    let minute = today.getMinutes();
    let late_check = "N";

    if (hour >= 9 && (hour > 9 || minute > 30)) late_check = "Y";

    const late_check_input = document.createElement("input");
    late_check_input.setAttribute("type", "hidden");
    late_check_input.setAttribute("name", "late_check");
    late_check_input.setAttribute("value", late_check);

    // form.appendChild(emp_id_input);
    form.appendChild(late_check_input);
    document.body.appendChild(form);
    form.submit();
});

work_end.addEventListener("click", () => {
    if (!(confirm("정말 사유 없이 퇴근합니까?"))) return;

    const form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "leave_work");

    const work_date_input = document.createElement("input");
    work_date_input.setAttribute("type", "hidden");
    work_date_input.setAttribute("name", "work_date");
    work_date_input.setAttribute("value", now_date);

    let end_hour = today.getHours();
    let end_minute = today.getMinutes();
    let work_end_time = end_hour + ":" + end_minute;

    const work_end_input = document.createElement("input");
    work_end_input.setAttribute("type", "hidden");
    work_end_input.setAttribute("name", "work_end");
    work_end_input.setAttribute("value", work_end_time);

    let work_start = attendance_row.rows[0].cells[1].innerText;
    let start_hour = parseInt(work_start.substring(0, 2));
    let start_minute = parseInt(work_start.substring(3, 5));
    let work_total_time = ((end_hour - start_hour) * 60) + (end_minute - start_minute);

    const work_total_input = document.createElement("input");
    work_total_input.setAttribute("type", "hidden");
    work_total_input.setAttribute("name", "work_total");
    work_total_input.setAttribute("value", work_total_time);

    form.appendChild(work_date_input);
    form.appendChild(work_end_input);
    form.appendChild(work_total_input);
    document.body.appendChild(form);
    form.submit();
});
