function loadTeacherGroups(teacherSelect) {
    console.log('loadTeacherGroups called');
    const teacherId = teacherSelect.value;
    console.log('Selected teacher ID:', teacherId);
    const groupSelect = document.getElementById('groupId');

    // Clear existing options
    groupSelect.innerHTML = '<option value="" disabled selected>Select Group</option>';

    if (teacherId) {
        fetch(`/api/v1/students/teachers/${teacherId}/groups`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(groups => {
                console.log('Received groups:', groups);
                if (groups.length === 0) {
                    console.log('No groups found for this teacher');
                }
                groups.forEach(group => {
                    const option = document.createElement('option');
                    option.value = group.id;
                    option.textContent = group.level ? group.level : `Group ${group.id}`;
                    groupSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error fetching groups:', error);
            });
    } else {
        console.log('No teacher selected');
    }
}
