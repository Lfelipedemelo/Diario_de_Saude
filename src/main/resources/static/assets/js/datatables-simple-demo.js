window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki
    
    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
        	labels: {
			    placeholder: "Pesquisar...",
			    perPage: "{select} vacinas por pagina",
			    noRows: "Nenhuma vacina encontrada",
			    info: "Mostrando {start} a {end} de {rows} vacinas",
			}
        });        
    }
});
