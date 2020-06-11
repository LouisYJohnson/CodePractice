package com.newcoder.zuo3.basic;

import java.util.LinkedList;

public class Class_02_DogCatQueue {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private int count;

        //猫和狗类不能改变,所以只能单独进行二次包装了,不过这次是加上了猫狗进入序列的标号count
        public PetEnterQueue(Pet pet, int count) {
            this.pet = pet;
            this.count = count;
        }

        //这个类需要有能够获取猫狗类以及对应加入时序号的方法
        public Pet getPet() {
            return this.pet;
        }

        public String getPetType() {
            return this.pet.getPetType();
        }

        public int getPetCount() {
            return this.count;
        }
    }

    //本类使用经过包装的猫狗类实现猫狗队列的结构
    public static class DogCatQueue {
        private LinkedList<PetEnterQueue> dogQ;
        private LinkedList<PetEnterQueue> catQ;
        private int count;

        public DogCatQueue() {
            dogQ = new LinkedList<PetEnterQueue>();
            catQ = new LinkedList<PetEnterQueue>();
            count = 0;
        }

        public void add(Pet pet) {
            if ("cat".equals(pet.getPetType())) {//如果是猫
                catQ.add(new PetEnterQueue(pet, count++));
            } else if ("dog".equals(pet.getPetType())) {//如果是猫
                dogQ.add(new PetEnterQueue(pet, count++));
            } else {
                throw new RuntimeException("type is not exist!");
            }
        }

        public Dog pollDog() {//将队列中dog类的实例按照进队列的先后顺序依次弹出
            if (!dogQ.isEmpty()) {
                return (Dog) dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty");
            }
        }

        public Cat pollCat() {//将队列中cat类的实例按照进队列的先后顺序依次弹出
            if (!catQ.isEmpty()) {
                return (Cat) catQ.poll().getPet();
            } else {
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty() {//检查队列中是否还有dog或cat的实例；
            if (catQ.isEmpty() && dogQ.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isDogEmpty() {//检查队列中是否有dog类的实例
            if (dogQ.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isCatEmpty() {//检查队列中是否有cat类的实例
            if (catQ.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

        public Pet pollAll() {//将队列中所有的实例按照进队列的先后顺序依次弹出
            if (!catQ.isEmpty() && !dogQ.isEmpty()) {
                if (catQ.peek().count > dogQ.peek().count) {
                    return catQ.poll().getPet();
                } else {
                    return dogQ.poll().getPet();
                }
            } else if (!catQ.isEmpty()) {
                return catQ.poll().getPet();
            } else if (!dogQ.isEmpty()) {
                return dogQ.poll().getPet();
            } else {
                throw new RuntimeException("queue is empty!");
            }
        }
    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }

    }
}
