async function fetchData() {
    try {
        const response = await fetch('http://localhost:8080/api');
        return await response.json();
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

async function renderData() {
    const container = document.querySelector('.container');
    const data = await fetchData();

    if (!data) {
        return;
    }

    data.forEach(item => {
        const card = document.createElement('div');
        card.classList.add('card');

        const title = document.createElement('h2');
        title.textContent = item.nome;

        const body = document.createElement('p');
        body.textContent = item.idade;

        card.appendChild(title);
        card.appendChild(body);
        container.appendChild(card);
    });
}

renderData();