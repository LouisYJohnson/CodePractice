package myCodePractice.zuo.basic.class07;

public class PrintAllSubsquences {
    //��ӡһ���ַ�����ȫ�������У� �������ַ���
    //�������������е�����,��·���ǿ�ǰ���λ�ö��ź���,��ǰλ����ô��
    public static void prinAllSubsequences(String str) {
        if (str == null) return;
        char[] chars = str.toCharArray();

    }

    //�ݹ麯������:
    //0-i-1λ���϶��Ѿ�ѡ����,iλ�õȴ�ȷ��,����ӡ���
    public static void process(char[] chars, int i) {
        //base case
        //����Ѿ����˾�ͷ,�Ͳ�����ѡ��,ֱ�ӽ��õ������д�ӡ������
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }

        //������ǰλ��i�ϵ�Ԫ��Ҫ���ǲ�Ҫ
        //Ҫ:
        process(chars, i + 1);
        //��Ҫ
        char temp = chars[i];
        chars[i] = 0;
        process(chars, i + 1);
        //�����һ���������͵ı����������޸�,�����޸��ڵݹ�����е�Ӱ����ȫ�ֵ�
        //����Ҫ�ڳ��˵ݹ�֮���ٸĻ���
        chars[i] = temp;
    }
}
