document.addEventListener("DOMContentLoaded", () => {
  const token = localStorage.getItem("token");

  if (token) {
      document.getElementById("btn-login").style.display = "none";
      document.getElementById("btn-cadastro").style.display = "none";
      document.getElementById("signin-mobile").style.display = "none";
      document.getElementById("register-mobile").style.display = "none";
      document.getElementById("account").classList.add('border-top');
  } else {
      document.getElementById("btn-conta").style.display = "none";
      document.getElementById("logount").style.display = "none";
      document.getElementById("logount-mobile").style.display = "none";
      document.getElementById("contact-link").classList.add('border-bottom');
      document.getElementById("payments").style.display = "none";
  }

  inicializarMenuHarburger();
//  logount();
});

function inicializarMenuHarburger() {
  const menuHarburger = document.getElementById('menu-harburger');
  const menuRightContainer = document.getElementById('menu-rigth-container');

  menuHarburger.addEventListener('click', (e) => {
      e.stopPropagation();
      menuHarburger.classList.remove('menu-harburger');
      menuHarburger.classList.add('menu-harburger-remove');
      menuRightContainer.classList.remove('menu-rigth-container');
      menuRightContainer.classList.add('menu-rigth-container-open');
  });

  menuRightContainer.addEventListener('click', (e) => {
      e.stopPropagation();
  });


  document.body.addEventListener('click', () => {
    menuHarburger.classList.remove('menu-harburger-remove');
    menuHarburger.classList.add('menu-harburger');
    menuRightContainer.classList.add('menu-rigth-container');
    menuRightContainer.classList.remove('menu-rigth-container-open');
  });
};

//function logount() {
//  const btnLogount = document.getElementById('logount');
//  const btnLogountMobile = document.getElementById('logount-mobile');
//
//  btnLogount.addEventListener('click', () => {
//      localStorage.removeItem("token");
//      window.location.href = "/home";
//  });
//
//  btnLogountMobile.addEventListener('click', () => {
//      localStorage.removeItem("token");
//      window.location.href = "/home";
//  })
//}
