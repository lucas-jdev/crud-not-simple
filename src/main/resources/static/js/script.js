const BASE_URL_API = 'http://localhost:8080/api';
const PATH_PEOPLE_API = BASE_URL_API + '/people';
const PATH_PEOPLE_FORM = '/people/form';

async function fetchData() {
    try {
        const response = await fetch(PATH_PEOPLE_API);
        return await response.json();
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

async function renderData() {
    const container = document.querySelector('#list-cards-people');
    const people = await fetchData();

    if (!people) {
        return;
    }

    console.log('values fetched:',people);

    people.forEach(person => {
        const card = document.createElement('div');
        card.classList.add('card');
        card.classList.add('p-3');
        card.classList.add('m-2');

        const title = document.createElement('div');
        title.classList.add('d-flex');
        title.classList.add('align-items-center');

        const name = document.createElement('p');
        name.classList.add('h2');
        name.classList.add('me-1');
        name.textContent = person.nome;
        const email = document.createElement('span');
        email.classList.add('text');
        email.classList.add('align-items-center');
        email.textContent = ` - ${person.email}`;

        title.appendChild(name);
        title.appendChild(email);

        const age = document.createElement('p');
        age.textContent = `Age: ${person.idade}`;

        card.appendChild(title);
        card.appendChild(age);

        const buttons = document.createElement('div');
        buttons.id = 'buttons';
        buttons.classList.add('d-grid');
        buttons.classList.add('gap-2');

        const buttonEdit = createButtonEdit(person.id);
        if (person.ativo) buttons.appendChild(buttonEdit);
        const buttonDelete = createButtonDelete(person.id);
        if (!person.ativo) {
            buttonDelete.classList.remove('btn-danger');
            buttonDelete.classList.add('btn-secondary');
            buttonDelete.classList.add('disabled');
            buttonDelete.textContent = 'Inactive';
        }
        buttons.appendChild(buttonDelete);

        card.appendChild(buttons);
        container.appendChild(card);
    });
}

function createButtonDelete(id) {
    const buttonDelete = document.createElement('button');
    buttonDelete.classList.add('btn');
    buttonDelete.classList.add('btn-danger');
    buttonDelete.textContent = 'Delete';
    eventClickButtonDelete(buttonDelete, id);
    return buttonDelete;
}

function eventClickButtonDelete(button, id) {
    button.addEventListener('click', async () => {
        button.textContent = 'Deleting...';
        button.classList.add('disabled');
        button.disabled = true
        const response = await fetch(`${PATH_PEOPLE_API}/${id}`, {
            method: 'DELETE',
        });
        if (response.ok) {
            button.classList.remove('btn-danger');
            button.classList.add('btn-secondary');
            button.textContent = 'Inactive';
            button.disabled = true;
            const editButtonEdit = document.querySelector(`#edit-${id}`);
            editButtonEdit.remove();
        }
    });
}

function createButtonEdit(id) {
    const buttonEdit = document.createElement('a');
    buttonEdit.id = `edit-${id}`;
    buttonEdit.classList.add('btn');
    buttonEdit.classList.add('btn-warning');
    buttonEdit.textContent = 'Edit';
    buttonEdit.disabled = true;
    buttonEdit.href = `${PATH_PEOPLE_FORM}?id=${id}`;

    return buttonEdit;
}

renderData();