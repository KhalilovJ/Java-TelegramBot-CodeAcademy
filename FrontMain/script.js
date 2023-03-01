let readMoreBtn = document.querySelectorAll(".cards_row button");
let popup = document.querySelector(".popup");
let closePopup = document.querySelector(".close-popup");
let loginForm = document.querySelector(".login-form");

if (loginForm) {
  loginForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let name = "Leila";
    let pass = "20052000";
    if (e.target.name.value === name && e.target.password.value === pass) {
      window.location.replace("info.html");
    } else {
      console.log("not submitted");
    }
  });
}

fetch("http://52.199.20.126:8080/api/v1/getAllSessions")
  .then((data) => data.json())
  .then((info) => {
    let data = "";
    info.map((element) => {
      data += `
      <div class="col-lg-6 col-md-6 col-sm-12 col-12 card-col">
      <div class="card">
        <ul>
          <li>
            <p>${element.id}</p>
            <span>${element.id}</span>
          </li>
          <li>
            <p>sual 1</p>
            <span>${element.answersList[0]}</span>
          </li>
          <li>
            <p>sual 2</p>
            <span>${element.answersList[1]}</span>
          </li>
          <li>
            <p>sual 3</p>
            <span>${element.answersList[2]}</span>
          </li>
          <li>
          <p>sual 4</p>
          <span>${element.answersList[3]}</span>
        </li>
        <li>
        <p>sual 5</p>
        <span>${element.answersList[4]}</span>
      </li>
      <li>
      <p>sual 6</p>
      <span>${element.answersList[5]}</span>
    </li>
    <li>
      <p>sual 7</p>
      <span>${element.answersList[6]}</span>
    </li>
    <li>
      <p>sual 8</p>
      <span>${element.answersList[7]}</span>
    </li>
    <li>
      <p>sual 9</p>
      <span>${element.answersList[8]}</span>
    </li>
        </ul>
        <button data=${element.id} class="msg_btn">Send message</button>
      </div>
    </div>`;
    });
    document.querySelector(".cards_row").innerHTML = data;
    if (document.querySelectorAll(".msg_btn")) {
      document.querySelectorAll(".msg_btn").forEach((btn) => {
        btn.addEventListener("click", (e) => {
          let a = "";
          a += `
                    <div class="popup">
    <div>
      <form class="send-message" action="">
        <div>
          <label for="send_msg">Write your message</label>
          <input name="hidden" type="hidden" value=${e.target.getAttribute("data")}>
          <textarea name="send_msg" id="send_msg" cols="35" rows="3" placeholder="write your message"></textarea>
        </div>
        <button class="msg_to_user">Send message</button>
      </form>
    </div>
  </div>
                    `;
          document.body.innerHTML += a;
          document.querySelector(".send-message").addEventListener("submit", (e) => {
            // e.preventDefault();
            fetch('http://52.199.20.126:8080/api/v1/sendoffer', {
              headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
                method: "POST",
                body: JSON.stringify({
                  "offerId": +e.target.hidden.value,
                  "offer": e.target.send_msg.value
                })
              })
              .then(res => res.json())
              .then(json => console.log(json))
          });
        });
      });
    }
  });