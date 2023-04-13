package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }


    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(",")){
                users.add(mapper.map(line));
            }else {
                users.add(mapper.map_2(line));
            }
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        user.setZnac(',');//что бы в вайле сохранялось через знак ","
//        user.setZnac(';');//что бы в вайле сохранялось через знак ";"
        users.add(user);

        saveUsers(users);
        return id;
    }

    private void saveUsers(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            if (item.getZnac()==','){
                lines.add(mapper.map(item));
            }else {
                lines.add(mapper.map_2(item));
            }
        }
        fileOperation.saveAllLines(lines);
    }

    public void UpdateUser(User user) {
        List<User> lis = getAllUsers();
        for (User per : lis) {
            if (per.getId().equals(user.getId())) {
                per.setFirstName(user.getFirstName());
                per.setLastName(user.getLastName());
                per.setPhone(user.getPhone());
            }
        }
        saveUsers(lis);
    }

    public void Delete(User user) {
        List<User> lis = getAllUsers();
        for (User us : lis) {
            if (us.getId().equals(user.getId())) {
                lis.remove(us);
                break;
            }
        }
        StabilId(lis);
        saveUsers(lis);
    }

    /**
     * При удалении записи - смещаются идентификаторы
     * @param list
     */
    private void StabilId(List<User> list){
        int flag = 1;
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).getId()) != flag){
                list.get(i).setId(Integer.toString(flag));
            }
            flag++;
        }
    }
}
