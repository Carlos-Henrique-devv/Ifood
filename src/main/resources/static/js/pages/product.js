document.addEventListener("DOMContentLoaded", () => {
    editProduct();
});

function editProduct() {
    const valueDATA = document.querySelectorAll('.editProduct');
    const cardProduct = document.getElementById('card-product');

    const previewImagem = document.querySelector('.imagemUrl');
    const inputName = document.querySelector('.name');
    const inputDescription = document.querySelector('.description');
    const inputPrice = document.querySelector('.price');

    valueDATA.forEach(button => {
        button.addEventListener("click", (event) => {

            const btn = event.target;

            const id = btn.dataset.id;
            const imagemUrlGet = btn.dataset.imagemurl;
            const name = btn.dataset.name;
            const description = btn.dataset.description;
            const price = btn.dataset.price;

            previewImagem.src = imagemUrlGet;
            inputName.value = name;
            inputDescription.value = description;
            inputPrice.value = price;

            cardProduct.classList.remove('card-product');
            cardProduct.classList.add('card-product-open');
        });
    });
}